package ru.pln.testtask.util;

import java.util.Objects;

public class Checker {
    public static boolean isEmptyString(String str){
        return Objects.isNull(str) || str.trim().length() == 0;
    }
}
