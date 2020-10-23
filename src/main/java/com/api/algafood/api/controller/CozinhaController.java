package com.api.algafood.api.controller;


import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.cozinha.CozinhaCompleta;
import com.api.algafood.api.model.representation.cozinha.CozinhaCompletaListagem;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.repository.CozinhaRepository;
import com.api.algafood.domain.service.CozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository repository;
    private CozinhaService service;
    private Converter<Cozinha,CozinhaCompletaListagem, CozinhaCompleta> assemblers;

    public CozinhaController(CozinhaRepository repository,
                             CozinhaService service,
                             Converter<Cozinha, CozinhaCompletaListagem, CozinhaCompleta> assemblers) {
        this.repository = repository;
        this.service = service;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<CozinhaCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(),CozinhaCompletaListagem.class);
    }

    @GetMapping("/{id}")
    public CozinhaCompletaListagem find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id),CozinhaCompletaListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaCompletaListagem save(@RequestBody @Valid CozinhaCompleta cozinhaCompleta) {
        var cozinha = assemblers.toDomainObject(cozinhaCompleta,Cozinha.class);
        return assemblers.toDTO(service.save(cozinha),CozinhaCompletaListagem.class);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
