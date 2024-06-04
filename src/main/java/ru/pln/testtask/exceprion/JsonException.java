package ru.pln.testtask.exceprion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonException {
    private String exception;
    private String message;
}
