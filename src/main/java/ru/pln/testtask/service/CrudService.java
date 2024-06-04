package ru.pln.testtask.service;

import ru.pln.testtask.dto.BaseDTO;

import java.util.List;

public interface CrudService<D extends BaseDTO> {
    D save(D d);
    List<D> findAll();
    D getById(Long i);
    void deleteById(Long i);
    D update(Long i, D d);
}
