package com.github.marciovmartins.crnk;

import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Controller
@Repository
public class TaskRepository extends JpaEntityRepositoryBase<Task, Long> {
    public TaskRepository() {
        super(Task.class);
    }
}
