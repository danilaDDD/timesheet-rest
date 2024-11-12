package ru.gbdanila.timesheetrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbdanila.timesheetrest.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByIdIn(List<Long> id);
}