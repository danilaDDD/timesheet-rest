package ru.gb.danila.timesheetrest.service;

import org.springframework.stereotype.Service;
import ru.gb.danila.timesheetrest.exceptions.EntityNotFoundException;
import ru.gb.danila.timesheetrest.model.Project;
import ru.gb.danila.timesheetrest.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements CRUDService<Project>{
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        Project existProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Project.class));

        existProject.setName(project.getName());

        return projectRepository.save(existProject);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
