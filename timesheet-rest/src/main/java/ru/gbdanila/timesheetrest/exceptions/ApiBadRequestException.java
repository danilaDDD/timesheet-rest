package ru.gbdanila.timesheetrest.exceptions;

public class ApiBadRequestException extends RuntimeException{
    public ApiBadRequestException(String message) {
        super(message);
    }
}
