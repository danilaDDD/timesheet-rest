package ru.gbdanila.timesheetrest.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }

    public <T>EntityNotFoundException(Long id, Class<T> clazz) {
        super(String.format("entity %s not found by id %s", clazz, id));
    }
}
