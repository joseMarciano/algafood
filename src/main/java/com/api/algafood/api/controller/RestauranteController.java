package com.api.algafood.api.controller;

import com.api.algafood.api.model.CozinhaDTO;
import com.api.algafood.api.model.RestauranteDTO;
import com.api.algafood.api.model.representation.restaurante.RestauranteCompleta;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Cozinha;
import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.restaurante.RestauranteRepository;
import com.api.algafood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RestauranteDTO> findAll() {
        return toCollectionDTO(repository.findAll());
    }

    @GetMapping("/{id}")
    public RestauranteDTO find(@PathVariable Long id) {
        Restaurante restaurante = service.findById(id);
        return toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO save(@RequestBody @Valid RestauranteCompleta restauranteCompleta) {
        try {
            var restaurante = toDomainObject(restauranteCompleta);
            return toDTO(service.save(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteDTO update(@PathVariable Long id,
                              @RequestBody RestauranteCompleta restauranteCompleta) {

        var restaurante = toDomainObject(restauranteCompleta);
        var entity = service.findById(id);
        BeanUtils.copyProperties(restaurante,entity,"id","dataHoraCadastroAtualizacao","endereco");
        return toDTO(service.save(entity));
    }


    private RestauranteDTO toDTO(Restaurante restaurante) {
        var cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        var restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteDTO.setCozinha(cozinhaDTO);
        return restauranteDTO;
    }

    private List<RestauranteDTO> toCollectionDTO (List<Restaurante> restaurantes){
        return restaurantes.stream().map(restaurante -> {
            return toDTO(restaurante);
        }).collect(Collectors.toList());
    }

    private Restaurante toDomainObject(RestauranteCompleta restauranteCompleta){
       var restaurante = new Restaurante();
        restaurante.setNome(restauranteCompleta.getNome());
        restaurante.setTaxaFrete(restauranteCompleta.getTaxaFrete());
        var cozinha = new Cozinha();
        cozinha.setId(restauranteCompleta.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return restaurante;
    }

}
