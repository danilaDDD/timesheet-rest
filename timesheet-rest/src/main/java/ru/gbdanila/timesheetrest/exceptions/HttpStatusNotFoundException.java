package ru.gbdanila.timesheetrest.exceptions;

public class HttpStatusNotFoundException extends RuntimeException{
    public HttpStatusNotFoundException(String message) {
        super(message);
    }
}
