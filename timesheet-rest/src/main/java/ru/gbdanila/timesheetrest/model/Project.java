package ru.gbdanila.timesheetrest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Schema(description = "Проект")
@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
public class Project {
    @Schema(description = "Идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @Schema(description = "Название")
    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
