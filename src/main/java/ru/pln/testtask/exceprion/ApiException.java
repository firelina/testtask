package ru.pln.testtask.exceprion;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
