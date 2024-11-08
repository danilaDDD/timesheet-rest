package ru.gb.danila.timesheetrest.exceptions;

public class HttpStatusNotFoundException extends RuntimeException{
    public HttpStatusNotFoundException(String message) {
        super(message);
    }
}
