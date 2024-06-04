package ru.pln.testtask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ru.pln.testtask.exceprion.ApiException;
import ru.pln.testtask.exceprion.JsonException;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {
    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<JsonException> apiException(ApiException exception, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity<>(new JsonException(exception.getClass().getSimpleName(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<JsonException> handleAllExceptions(Exception exception, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity<>(new JsonException(exception.getClass().getSimpleName(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
