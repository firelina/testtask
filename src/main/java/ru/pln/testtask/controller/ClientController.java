package ru.pln.testtask.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.testtask.dto.ClientDTO;
import ru.pln.testtask.search.ClientSearch;
import ru.pln.testtask.service.ClientSearchService;
import ru.pln.testtask.service.ClientService;
import ru.pln.testtask.service.CrudService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final CrudService<ClientDTO> service;
    private final ClientSearchService searchService;

    public ClientController(@Qualifier("clientServiceImpl") CrudService<ClientDTO> service, ClientSearchService searchService) {
        this.service = service;
        this.searchService = searchService;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientDTO> save(@RequestBody(required = true) ClientDTO object){
        return ResponseEntity.ok(service.save(object));
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable(name = "id") Long id,
                                            @RequestBody(required = true) ClientDTO object){
        return ResponseEntity.ok(service.update(id, object));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/clients")
    public ResponseEntity<Page<ClientDTO>> search(@RequestBody ClientSearch search) {
        return ResponseEntity.ok(searchService.search(search));
    }
}
