package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.grupo.GrupoCompleta;
import com.api.algafood.api.model.representation.grupo.GrupoCompletaListagem;
import com.api.algafood.domain.model.Grupo;
import com.api.algafood.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private UsuarioService usuarioService;
    private Converter<Grupo, GrupoCompletaListagem, GrupoCompleta> assemblers;

    public UsuarioGrupoController(UsuarioService usuarioService,
                                  Converter<Grupo, GrupoCompletaListagem, GrupoCompleta> assemblers) {
        this.usuarioService = usuarioService;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<GrupoCompletaListagem> listAllGruposEmUsuario(@PathVariable Long usuarioId) {
        return assemblers.toCollectionDTO(usuarioService.listAllGrupos(usuarioId), GrupoCompletaListagem.class);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarGrupo(@PathVariable Long usuarioId,
                               @PathVariable Long grupoId){
        usuarioService.associarGrupo(usuarioId,grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerGrupo(@PathVariable Long usuarioId,
                               @PathVariable Long grupoId){
        usuarioService.desassociarGrupo(usuarioId,grupoId);
    }

}
