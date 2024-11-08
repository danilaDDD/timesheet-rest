package ru.gb.danila.timesheetrest.service;

import org.springframework.stereotype.Service;
import ru.gb.danila.timesheetrest.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheetrest.model.Timesheet;
import ru.gb.danila.timesheetrest.repository.ProjectRepository;
import ru.gb.danila.timesheetrest.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TimesheetService implements CRUDService<Timesheet> {
    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    @Override
    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    @Override
    public Timesheet create(Timesheet timesheet) {
        Long projectId = timesheet.getProjectId();
        if(projectId == null)
            throw new IllegalArgumentException("projectId is not null");
        projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("project with this project id is not found"));

        return timesheetRepository.save(timesheet);
    }

    @Override
    public Timesheet update(Long id, Timesheet timesheet) {
        Timesheet existTimesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Timesheet.class));

        existTimesheet.setMinutes(timesheet.getMinutes());
        existTimesheet.setProjectId(timesheet.getProjectId());
        existTimesheet.setEmployeeId(timesheet.getEmployeeId());

        return timesheetRepository.save(existTimesheet);
    }

    @Override
    public void delete(Long id) {
        timesheetRepository.deleteById(id);
    }

    public List<Timesheet> findCreatedAtAfter(LocalDate createdAtAfter) {
        return timesheetRepository.findAllByCreatedAtAfter(createdAtAfter);
    }

    public List<Timesheet> findCreatedAtBefore(LocalDate createdAtBefore) {
        return timesheetRepository.findAllByCreatedAtBefore(createdAtBefore);
    }

    public List<Timesheet> findTimesheetsByProjectId(Long projectId) {
        return timesheetRepository.findAllByProjectId(projectId);
    }
}
