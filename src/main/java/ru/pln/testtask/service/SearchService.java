package ru.pln.testtask.service;

import org.springframework.data.domain.Page;
import ru.pln.testtask.dto.BaseDTO;
import ru.pln.testtask.search.BaseSearch;

public interface SearchService<D extends BaseDTO, S extends BaseSearch> {
    Page<D> search(S obj);
}
