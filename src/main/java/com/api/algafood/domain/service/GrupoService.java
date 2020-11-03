package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Grupo;
import com.api.algafood.domain.repository.GrupoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {

    private final String MSG_GRUPO_NAO_ENCONTRADO =
            "Entity 'Grupo' with identifier %d not found";

    private final String MSG_GRUPO_EM_USO =
            "Entity 'Grupo' with identifier %d is in use";

    private GrupoRepository repository;
    private PermissaoService permissaoService;

    public GrupoService(GrupoRepository repository,
                        PermissaoService permissaoService) {
        this.repository = repository;
        this.permissaoService = permissaoService;
    }

    public Grupo findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(MSG_GRUPO_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public Grupo save(Grupo grupo) {
        return repository.save(grupo);
    }

    @Transactional
    public void remove(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_GRUPO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        var grupo = findById(grupoId);
        var permissao = permissaoService.findById(permissaoId);
        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId){
        var grupo = findById(grupoId);
        var permissao = permissaoService.findById(permissaoId);
        grupo.adicionarPermissao(permissao);
    }
}
