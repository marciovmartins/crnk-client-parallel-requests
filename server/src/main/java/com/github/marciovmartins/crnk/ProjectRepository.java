package com.github.marciovmartins.crnk;

import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Controller
@Repository
public class ProjectRepository extends JpaEntityRepositoryBase<Project, Long> {
    public ProjectRepository() {
        super(Project.class);
    }
}
