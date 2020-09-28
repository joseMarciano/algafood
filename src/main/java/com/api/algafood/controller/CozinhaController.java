package com.api.algafood.controller;


import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.domain.service.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository repository;
    private CozinhaService service;

    public CozinhaController(CozinhaRepository repository, CozinhaService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save(@RequestBody Cozinha cozinha) {
        return service.save(cozinha);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
