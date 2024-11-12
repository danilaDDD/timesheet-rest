package ru.gbdanila.timesheetrest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbdanila.timesheetrest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
