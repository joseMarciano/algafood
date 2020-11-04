package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.usuario.UsuarioCompleta;
import com.api.algafood.api.model.representation.usuario.UsuarioListagem;
import com.api.algafood.domain.model.Usuario;
import com.api.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController {

    private RestauranteService restauranteService;
    private Converter<Usuario,UsuarioListagem, UsuarioCompleta> assemblers;

    public RestauranteUsuarioController(RestauranteService restauranteService,
                                        Converter<Usuario, UsuarioListagem, UsuarioCompleta> assemblers) {
        this.restauranteService = restauranteService;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<UsuarioListagem> listAllUsuariosEmRestaurante(@PathVariable Long restauranteId){
        var usuarios = restauranteService.listAllUsuarios(restauranteId);
        return assemblers.toCollectionDTO(usuarios,UsuarioListagem.class);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarUsuarioEmRestaurante(@PathVariable Long restauranteId,
                                             @PathVariable Long usuarioId){
        restauranteService.associarUsuario(restauranteId,usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarUsuarioEmRestaurante(@PathVariable Long restauranteId,
                                             @PathVariable Long usuarioId){
        restauranteService.desassociarUsuario(restauranteId,usuarioId);
    }


}
