package ru.gbdanila.timesheetrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbdanila.timesheetrest.exceptions.ApiBadRequestException;
import ru.gbdanila.timesheetrest.exceptions.EntityNotFoundException;
import ru.gbdanila.timesheetrest.model.Project;
import ru.gbdanila.timesheetrest.model.Timesheet;
import ru.gbdanila.timesheetrest.service.ProjectService;
import ru.gbdanila.timesheetrest.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Проекты", description = "АПИ проектов")
@RestController
@RequestMapping("/projects")
public class ProjectController {
   private final ProjectService projectService;
   private final TimesheetService timesheetService;

    public ProjectController(ProjectService projectService, TimesheetService timesheetService) {
        this.projectService = projectService;
        this.timesheetService = timesheetService;
    }

    @Operation(summary = "Проект", description = "Получение проекта по идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        Optional<Project> projectOptional = projectService.findById(id);

        return projectOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException(id, Project.class));
    }

    @Operation(summary = "Список проектов", description = "Получение всех проектов")
    @GetMapping
    public List<Project> findAll(){
        return projectService.findAll();
    }

    @Operation(summary = "Список временных меток", description = "Получение временных меток проекта")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findTimesheetsByProjectId(@PathVariable @Parameter(description = "") Long id){
        try{
            return ResponseEntity.ok(timesheetService.findTimesheetsByProjectId(id));
        }catch (NoSuchElementException e){
            throw new ApiBadRequestException(String.format("project by id %s not found", id));
        }
    }

    @Operation(summary = "Созданный проект", description = "Создание нового проекта")
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        return ResponseEntity.ok(projectService.create(project));
    }

    @Operation(summary = "Обновленный проект", description = "Обновление существующего проекта")
    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable @Parameter(description = "Идентификатор проекта") Long id, @RequestBody Project project){
        try {
            return ResponseEntity.ok(projectService.update(id, project));
        }catch (NoSuchElementException e){
            throw new ApiBadRequestException(String.format("project by id %s not found", id));
        }
    }

    @Operation(summary = "Постота", description = "Удаление проекта по идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        try{
            projectService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            throw new ApiBadRequestException(String.format("project by id %s not found", id));
        }
    }
}
