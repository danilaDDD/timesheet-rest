package ru.gbdanila.timesheetrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbdanila.timesheetrest.model.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
  List<UserRole> findAllByUserId(Long id);
}