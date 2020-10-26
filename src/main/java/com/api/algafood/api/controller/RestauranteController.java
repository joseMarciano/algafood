package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompletaListagem;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import com.api.algafood.domain.service.CozinhaService;
import com.api.algafood.domain.service.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository repository;
    private RestauranteService service;
    private Converter<Restaurante,RestauranteCompletaListagem,RestauranteCompleta> assemblers;
    private ModelMapper modelMapper;
    private CozinhaService cozinhaService;

    public RestauranteController(RestauranteRepository repository,
                                 RestauranteService service,
                                 Converter<Restaurante, RestauranteCompletaListagem, RestauranteCompleta> assemblers,
                                 ModelMapper modelMapper,
                                 CozinhaService cozinhaService) {
        this.repository = repository;
        this.service = service;
        this.assemblers = assemblers;
        this.modelMapper = modelMapper;
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public List<RestauranteCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(),RestauranteCompletaListagem.class);
    }

    @GetMapping("/{id}")
    public RestauranteCompletaListagem find(@PathVariable Long id) {
        Restaurante restaurante = service.findById(id);
        return assemblers.toDTO(restaurante,RestauranteCompletaListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteCompletaListagem save(@RequestBody @Valid RestauranteCompleta restauranteCompleta) {
        try {
            var restaurante = assemblers.toDomainObject(restauranteCompleta,Restaurante.class);
            return assemblers.toDTO(service.save(restaurante),RestauranteCompletaListagem.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteCompletaListagem update(@PathVariable Long id,
                                              @RequestBody RestauranteCompleta restauranteCompleta) {

        var restaurante = service.findById(id);
        copyToDomainObject(restauranteCompleta,restaurante);
        return assemblers.toDTO(service.save(restaurante),RestauranteCompletaListagem.class);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id){
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id){
        service.inativar(id);
    }

    private void copyToDomainObject(RestauranteCompleta restauranteCompleta, Restaurante restaurante) {
    /* Para evitar Caused by: org.hibernate.HibernateException: identifier of an instance of
     * com.api.algafood.domain.model.Cozinha was altered from 1 to 2
     * Poderia ser resolvido assim: restaurante.setCozinha(new Cozinha()), mas como estou com problemas com o
     * retorno do nome da cozinha = null no json, encontrei essa maneira de contornar o problema
     */
        Cozinha cozinha = cozinhaService.findById(restauranteCompleta.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        modelMapper.map(restauranteCompleta, restaurante);
    }

}
