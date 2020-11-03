package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.permissao.PermissaoCompleta;
import com.api.algafood.api.model.representation.permissao.PermissaoListagem;
import com.api.algafood.domain.model.Permissao;
import com.api.algafood.domain.service.GrupoService;
import com.api.algafood.domain.service.PermissaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    private GrupoService grupoService;
    private PermissaoService permissaoService;
    private Converter<Permissao, PermissaoListagem, PermissaoCompleta> assemblers;

    public GrupoPermissaoController(GrupoService grupoService,
                                    Converter<Permissao, PermissaoListagem, PermissaoCompleta> assemblers) {
        this.grupoService = grupoService;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<PermissaoListagem> listAllPermissoesEmGrupo(@PathVariable Long grupoId) {
        var grupo = grupoService.findById(grupoId);
        return assemblers.toCollectionDTO(grupo.getPermissoes(), PermissaoListagem.class);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId,
                            @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId,permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId,
                            @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId,permissaoId);
    }

}
