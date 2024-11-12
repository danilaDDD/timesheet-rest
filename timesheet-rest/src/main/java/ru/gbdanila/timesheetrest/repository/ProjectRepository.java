package ru.gbdanila.timesheetrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbdanila.timesheetrest.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
