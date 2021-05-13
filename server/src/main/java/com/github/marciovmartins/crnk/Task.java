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
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonApiResource(type = "tasks")
public class Task {
    @Id
    @JsonApiId
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonApiRelation(mappedBy = "tasks")
    Project project;
}