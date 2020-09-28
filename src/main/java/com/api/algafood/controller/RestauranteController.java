package com.api.algafood.controller;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import com.api.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository repository;
    private RestauranteService service;

    public RestauranteController(RestauranteRepository repository, RestauranteService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurante find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante save(@RequestBody Restaurante restaurante) {
        try {
            return service.save(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


}
