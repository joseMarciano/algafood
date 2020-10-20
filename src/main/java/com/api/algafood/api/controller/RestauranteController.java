package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.RestauranteAssemblers;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompletaListagem;
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
    private RestauranteAssemblers assemblers;

    public RestauranteController(RestauranteRepository repository,
                                 RestauranteService service, RestauranteAssemblers assemblers) {
        this.repository = repository;
        this.service = service;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<RestauranteCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll());
    }

    @GetMapping("/{id}")
    public RestauranteCompletaListagem find(@PathVariable Long id) {
        Restaurante restaurante = service.findById(id);
        return assemblers.toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteCompletaListagem save(@RequestBody @Valid RestauranteCompleta restauranteCompleta) {
        try {
            var restaurante = assemblers.toDomainObject(restauranteCompleta);
            return assemblers.toDTO(service.save(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteCompletaListagem update(@PathVariable Long id,
                                              @RequestBody RestauranteCompleta restauranteCompleta) {

        var restaurante = assemblers.toDomainObject(restauranteCompleta);
        var entity = service.findById(id);
        BeanUtils.copyProperties(restaurante, entity, "id", "dataHoraCadastroAtualizacao", "endereco");
        return assemblers.toDTO(service.save(entity));
    }


}
