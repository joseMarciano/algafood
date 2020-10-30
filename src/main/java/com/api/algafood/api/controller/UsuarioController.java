package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.usuario.UsuarioCompleta;
import com.api.algafood.api.model.representation.usuario.UsuarioCompletaUpdate;
import com.api.algafood.api.model.representation.usuario.UsuarioListagem;
import com.api.algafood.api.model.representation.usuario.UsuarioSenhaUpdate;
import com.api.algafood.domain.Exception.EmailExistenteException;
import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Usuario;
import com.api.algafood.domain.repository.UsuarioRepository;
import com.api.algafood.domain.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UsuarioRepository repository;
    private Converter<Usuario, UsuarioListagem, UsuarioCompleta> assemblers;
    private UsuarioService service;
    private ModelMapper modelMapper;

    public UsuarioController(UsuarioRepository repository,
                             Converter<Usuario, UsuarioListagem, UsuarioCompleta> assemblers,
                             UsuarioService service, ModelMapper modelMapper) {
        this.repository = repository;
        this.assemblers = assemblers;
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UsuarioListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(), UsuarioListagem.class);
    }

    @GetMapping("/{id}")
    public UsuarioListagem find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id), UsuarioListagem.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioListagem save(@RequestBody @Valid UsuarioCompleta usuarioCompleta) {
        try{
            var usuario = assemblers.toDomainObject(usuarioCompleta, Usuario.class);
            return assemblers.toDTO(service.save(usuario), UsuarioListagem.class);
        } catch (EmailExistenteException e){
            throw  new NegocioException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public UsuarioListagem update(@PathVariable Long id,
                                  @RequestBody @Valid UsuarioCompletaUpdate usuarioCompletaUpdate) {
        try {
            var usuario = service.findById(id);
            copyToDomainObject(usuarioCompletaUpdate, usuario);
            return assemblers.toDTO(service.save(usuario), UsuarioListagem.class);
        } catch (EntidadeNaoEncontradaException | EmailExistenteException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            var usuario = service.findById(id);
            service.remove(usuario.getId());
        } catch (EntidadeNaoEncontradaException | EntidadeEmUsoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}/senha")
    public UsuarioListagem changePassword(@PathVariable Long id,
                                          @RequestBody @Valid UsuarioSenhaUpdate usuarioSenhaUpdate) {
        try {
            var usuario = service.updateSenha(id, usuarioSenhaUpdate.getSenhaAtual(), usuarioSenhaUpdate.getNovaSenha());
           return assemblers.toDTO(usuario,UsuarioListagem.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        } catch (NegocioException e){
            throw new NegocioException(e.getMessage());
        }
    }

    private void copyToDomainObject(UsuarioCompletaUpdate usuarioCompletaUpdate, Usuario usuario) {
        modelMapper.map(usuarioCompletaUpdate, usuario);
    }
}
