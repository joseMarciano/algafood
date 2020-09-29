package com.api.algafood.controller;

import com.api.algafood.domain.model.Estado;
import com.api.algafood.domain.repository.EstadoRepository;
import com.api.algafood.domain.service.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository repository;
    private EstadoService service;

    public EstadoController(EstadoRepository repository, EstadoService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Estado find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado save(@RequestBody @Valid Estado estado) {
        return service.save(estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }
}
