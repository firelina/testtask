package ru.pln.testtask.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.testtask.dto.BankDTO;
import ru.pln.testtask.search.BankSearch;
import ru.pln.testtask.search.BaseSearch;
import ru.pln.testtask.service.BankService;
import ru.pln.testtask.service.CrudService;
import ru.pln.testtask.service.SearchService;

@RestController
@RequestMapping("/banks")
public class BankController {
    private final CrudService<BankDTO> service;
    private final SearchService<BankDTO, BankSearch> bankSearchService;
    public BankController(@Qualifier("bankServiceImpl") CrudService<BankDTO> service, SearchService<BankDTO, BankSearch> bankSearchService) {
        this.service = service;
        this.bankSearchService = bankSearchService;
    }
    @PostMapping("/bank")
    public ResponseEntity<BankDTO> save(@RequestBody(required = true) BankDTO object){
        return ResponseEntity.ok(service.save(object));
    }
    @PutMapping("/bank/{id}")
    public ResponseEntity<BankDTO> update(@PathVariable(name = "id") Long id,
                                            @RequestBody(required = true) BankDTO object){
        return ResponseEntity.ok(service.update(id, object));
    }
    @DeleteMapping("/bank/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/bank/{id}")
    public ResponseEntity<BankDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping("/banks")
    public ResponseEntity<Page<BankDTO>> search(@RequestBody BankSearch search){
        return ResponseEntity.ok(bankSearchService.search(search));
    }

}
