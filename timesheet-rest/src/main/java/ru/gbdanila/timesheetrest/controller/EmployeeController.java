package ru.gbdanila.timesheetrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbdanila.timesheetrest.exceptions.EntityNotFoundException;
import ru.gbdanila.timesheetrest.model.Employee;
import ru.gbdanila.timesheetrest.model.Timesheet;
import ru.gbdanila.timesheetrest.service.EmployeeService;
import ru.gbdanila.timesheetrest.service.TimesheetService;

import java.util.List;

@Tag(name = "Сотрудники", description = "АПИ Сотрудников")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TimesheetService timesheetService;

    @Operation(summary = "Список сотрудников", description = "Получение всех сотрудников")
    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @Operation(description = "Получение сотрудника по идентификатору", summary = "Сотрудник")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id){
        return employeeService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException(id, Employee.class));
    }

    @Operation(description = "Создание сотрудника", summary = "Сотрудник")
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @Operation(summary = "Сотрудник", description = "Обновление сотрудника")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable @Parameter(description = "") Long id){

        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @Operation(summary = "Сотрудник", description = "Список временный меток сотрудника")
    @GetMapping("/{id}/timesheets")
    public List<Timesheet> findTimesheetsByEmployeeId(@PathVariable @Parameter(description = "") Long id){
       return timesheetService.findTimesheetsByProjectId(id);
    }
    @Operation(summary = "Сотрудник", description = "Удаление сотрудника по идентификатору")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id){
        employeeService.delete(id);
    }
}
