package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.estado.EstadoCompleta;
import com.api.algafood.api.model.representation.estado.EstadoCompletaListagem;
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
    private Converter<Estado, EstadoCompletaListagem, EstadoCompleta> assemblers;

    public EstadoController(EstadoRepository repository,
                            EstadoService service,
                            Converter<Estado, EstadoCompletaListagem, EstadoCompleta> assemblers) {
        this.repository = repository;
        this.service = service;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<EstadoCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(),EstadoCompletaListagem.class);
    }

    @GetMapping("/{id}")
    public EstadoCompletaListagem find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id),EstadoCompletaListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoCompletaListagem save(@RequestBody @Valid EstadoCompleta estadoCompleta) {
        var estado = assemblers.toDomainObject(estadoCompleta,Estado.class);
        return assemblers.toDTO(service.save(estado),EstadoCompletaListagem.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }
}
