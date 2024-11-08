package ru.gb.danila.timesheetrest.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<Entity> {
    List<Entity> findAll();

    Optional<Entity> findById(Long id);

    Entity create(Entity entity);

    Entity update(Long id, Entity entity);

    void delete(Long id);
}
