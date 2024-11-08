package ru.gb.danila.timesheetrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.danila.timesheetrest.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findAllByCreatedAtAfter(LocalDate createdAt);

    List<Timesheet> findAllByCreatedAtBefore(LocalDate createdAt);

    List<Timesheet> findAllByProjectId(Long projectId);

    List<Timesheet> findAllByEmployeeId(Long employeeId);
}
