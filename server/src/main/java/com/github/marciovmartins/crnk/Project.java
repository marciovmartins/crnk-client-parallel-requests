package com.github.marciovmartins.crnk;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonApiResource(type = "project")
public class Project {
    @Id
    @JsonApiId
    Long id;

    String name;

    @OneToMany
    @JoinColumn(name = "project_id")
    @JsonApiRelation(mappedBy = "project")
    List<Task> tasks;
}
