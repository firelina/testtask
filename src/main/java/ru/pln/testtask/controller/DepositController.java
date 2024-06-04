package ru.pln.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.dto.DepositDTO;
import ru.pln.testtask.search.ClientSearch;
import ru.pln.testtask.search.DepositSearch;
import ru.pln.testtask.service.CrudService;
import ru.pln.testtask.service.DepositSearchService;
import ru.pln.testtask.service.DepositService;

@RestController
@RequestMapping("/deposits")
public class DepositController {
    private final CrudService<DepositDTO> service;

    public DepositController(@Qualifier("depositServiceImpl") CrudService<DepositDTO> service, DepositSearchService searchService) {
        this.service = service;
        this.searchService = searchService;
    }

    private final DepositSearchService searchService;
    @PostMapping("/deposit")
    public ResponseEntity<DepositDTO> save(@RequestBody(required = true) DepositDTO object){
        return ResponseEntity.ok(service.save(object));
    }
    @PutMapping("/deposit/{id}")
    public ResponseEntity<DepositDTO> update(@PathVariable(name = "id") Long id,
                                            @RequestBody(required = true) DepositDTO object){
        return ResponseEntity.ok(service.update(id, object));
    }

    @DeleteMapping("/deposit/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deposit/{id}")
    public ResponseEntity<DepositDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/deposits")
    public ResponseEntity<Page<DepositDTO>> search(@RequestBody DepositSearch search) {
        return ResponseEntity.ok(searchService.search(search));
    }
}
