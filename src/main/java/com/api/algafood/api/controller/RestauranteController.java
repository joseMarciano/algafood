package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.RestauranteModelAssembler;
import com.api.algafood.api.assembler.RestauranteModelDisassembler;
import com.api.algafood.api.model.RestauranteDTO;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import com.api.algafood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository repository;
    private RestauranteService service;
    private RestauranteModelAssembler assembler;
    private RestauranteModelDisassembler disassembler;

    public RestauranteController(RestauranteRepository repository,
                                 RestauranteService service,
                                 RestauranteModelAssembler assembler,
                                 RestauranteModelDisassembler disassembler) {
        this.repository = repository;
        this.service = service;
        this.assembler = assembler;
        this.disassembler = disassembler;
    }

    @GetMapping
    public List<RestauranteDTO> findAll() {
        return assembler.toCollectionDTO(repository.findAll());
    }

    @GetMapping("/{id}")
    public RestauranteDTO find(@PathVariable Long id) {
        Restaurante restaurante = service.findById(id);
        return assembler.toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO save(@RequestBody @Valid RestauranteCompleta restauranteCompleta) {
        try {
            var restaurante = disassembler.toDomainObject(restauranteCompleta);
            return assembler.toDTO(service.save(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDTO update(@PathVariable Long id,
                              @RequestBody RestauranteCompleta restauranteCompleta) {

        var restaurante = disassembler.toDomainObject(restauranteCompleta);
        var entity = service.findById(id);
        BeanUtils.copyProperties(restaurante,entity,"id","dataHoraCadastroAtualizacao","endereco");
        return assembler.toDTO(service.save(entity));
    }



}
