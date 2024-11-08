package ru.gb.danila.timesheetrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.danila.timesheetrest.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheetrest.model.Employee;
import ru.gb.danila.timesheetrest.model.Timesheet;
import ru.gb.danila.timesheetrest.repository.EmployeeRepository;
import ru.gb.danila.timesheetrest.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService implements CRUDService<Employee> {
    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee existEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Employee.class));

        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setSecondName(employee.getSecondName());
        existEmployee.setProjects(Set.copyOf(employee.getProjects()));

        return employeeRepository.save(existEmployee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Timesheet> findTimesheetsByEmployeeId(Long employeeId){
        return timesheetRepository.findAllByEmployeeId(employeeId);
    }
}
