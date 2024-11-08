package ru.gb.danila.timesheetrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.danila.timesheetrest.model.Timesheet;
import ru.gb.danila.timesheetrest.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Временные метки", description = "АПИ временных меток")
@RestController
@RequestMapping("/timesheets")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @Operation(summary = "Список временных меток", description = "Получение всех временных меток")
    @GetMapping
    public List<Timesheet> findAll(
            @RequestParam(name = "created-at-after", required = false) @Parameter(description = "Дата после") LocalDate createdAtAfter,
            @RequestParam(name = "created-at-before", required = false) @Parameter(description = "Дата до") LocalDate createdAtBefore
            ){
        if(createdAtAfter != null){
            return timesheetService.findCreatedAtAfter(createdAtAfter);
        } else if (createdAtBefore != null) {
            return timesheetService.findCreatedAtBefore(createdAtBefore);
        }
        return timesheetService.findAll();
    }

    @Operation(summary = "Временная метка", description = "Получение временной метки по идентификатору")
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable @Parameter(description = "Идентификатор временной метки") Long id){
       Optional<Timesheet> timesheetOptional = timesheetService.findById(id);

       return timesheetOptional.map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());

   }

    @Operation(summary = "Созданная временная метка", description = "Создание новой временной метки")
    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet){
        try {
            Timesheet savedTimesheet = timesheetService.create(timesheet);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTimesheet);
        } catch (NoSuchElementException | IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
   }

    @Operation(summary = "Обновленная временная метка", description = "Обновление времеенной метки")
    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> update(@PathVariable @Parameter(description = "Идентификатор временной метки") Long id, @RequestBody Timesheet timesheet){
        try {
            return ResponseEntity.ok(timesheetService.update(id, timesheet));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Ничего", description = "Удаление временной метки")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор временной метки") Long id){
        try {
            timesheetService.delete(id);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().build();
        }
   }
}
