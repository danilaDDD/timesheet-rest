package ru.gb.danila.timesheetrest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Временная метка")
@Entity
@Table(name = "timesheet")
@Data
@NoArgsConstructor
public class Timesheet {
    @Schema(description = "Идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @Schema(description = "Идентификатор проекта")
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Schema(description = "количество минут")
    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Schema(description = "Дата создания")
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Schema(description = "Идентификатор сотрудника")
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    public Timesheet(int minutes, Long projectId, Long employeeId) {
        this.minutes = minutes;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.createdAt = LocalDate.now();
    }
}
