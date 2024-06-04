package ru.pln.testtask.search;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseSearch implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
