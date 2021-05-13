package com.github.marciovmartins.crnk;

import com.github.javafaker.Faker;
import io.crnk.client.CrnkClient;
import io.crnk.core.repository.ResourceRepository;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.Executors;

public class CreateUsingCrnkClientTest {
    private static final Faker faker = Faker.instance();
    private final CrnkClient client = new CrnkClient("http://localhost:8080");
    private final ResourceRepository<Project, Object> projectRepository = client.getRepositoryForType(Project.class);
    private final ResourceRepository<Task, Object> taskRepository = client.getRepositoryForType(Task.class);

    @Test
    void singleThread() {
        var newProject = new Project.ProjectBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.superhero().name())
                .build();

        var newTask = new Task.TaskBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.animal().name())
                .project(newProject)
                .build();

        projectRepository.create(newProject);
        taskRepository.create(newTask);
    }

    @Test
    void multiThreadSameRepositoryInstance() throws Exception {
        var newProject = new Project.ProjectBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.superhero().name())
                .build();

        var newTask = new Task.TaskBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.animal().name())
                .project(newProject)
                .build();

        var pool = Executors.newFixedThreadPool(2);
        var projectFuture = pool.submit(() -> projectRepository.create(newProject));
        var taskFuture = pool.submit(() -> taskRepository.create(newTask));

        projectFuture.get();
        taskFuture.get();
    }

    @Test
    void multiThreadSameRepositoryInstance2() throws Exception {
        var newProject = new Project.ProjectBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.superhero().name())
                .build();

        var newTask = new Task.TaskBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.animal().name())
                .project(newProject)
                .build();

        var pool = Executors.newFixedThreadPool(2);
        var futures = pool.invokeAll(Set.of(
                () -> projectRepository.create(newProject),
                () -> taskRepository.create(newTask)
        ));

        futures.get(0).get();
        futures.get(1).get();
    }

    @Test
    void multiThreadDifferentRepositoryInstance() throws Exception {
        var newProject = new Project.ProjectBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.superhero().name())
                .build();

        var newTask = new Task.TaskBuilder()
                .id(faker.random().nextInt(10000, 65535).longValue())
                .name(faker.animal().name())
                .project(newProject)
                .build();

        var projectRepository2 = client.getRepositoryForType(Project.class);
        var taskRepository2 = client.getRepositoryForType(Task.class);

        var pool = Executors.newFixedThreadPool(2);
        var projectFuture = pool.submit(() -> projectRepository2.create(newProject));
        var taskFuture = pool.submit(() -> taskRepository2.create(newTask));

        projectFuture.get();
        taskFuture.get();
    }
}
