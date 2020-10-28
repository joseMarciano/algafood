package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.grupo.GrupoCompleta;
import com.api.algafood.api.model.representation.grupo.GrupoCompletaListagem;
import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Grupo;
import com.api.algafood.domain.repository.GrupoRepository;
import com.api.algafood.domain.service.GrupoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private GrupoRepository repository;
    private Converter<Grupo, GrupoCompletaListagem, GrupoCompleta> assemblers;
    private GrupoService service;
    private ModelMapper modelMapper;

    public GrupoController(GrupoRepository repository,
                           Converter<Grupo, GrupoCompletaListagem, GrupoCompleta> assemblers,
                           GrupoService service,
                           ModelMapper modelMapper) {
        this.repository = repository;
        this.assemblers = assemblers;
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<GrupoCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(), GrupoCompletaListagem.class);
    }

    @GetMapping("/{id}")
    public GrupoCompletaListagem find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id), GrupoCompletaListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoCompletaListagem save(@RequestBody GrupoCompleta grupoCompleta) {
        var grupo = assemblers.toDomainObject(grupoCompleta, Grupo.class);
        return assemblers.toDTO(service.save(grupo), GrupoCompletaListagem.class);
    }

    @PutMapping("/{id}")
    public GrupoCompletaListagem update(@PathVariable Long id,
                                        @RequestBody GrupoCompleta grupoCompleta) {
        try {
            var grupo = service.findById(id);
            copyToDomainObject(grupoCompleta, grupo);
            return assemblers.toDTO(service.save(grupo), GrupoCompletaListagem.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            service.remove(id);
        }catch(EntidadeNaoEncontradaException | EntidadeEmUsoException e){
            throw new NegocioException(e.getMessage());
        }
    }

    private void copyToDomainObject(GrupoCompleta grupoCompleta, Grupo grupo) {
        modelMapper.map(grupoCompleta, grupo);
    }
}
