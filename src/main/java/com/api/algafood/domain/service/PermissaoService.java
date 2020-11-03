package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Permissao;
import com.api.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {


    private final String MSG_PERMISSAO_NAO_ENCONTRADA =
            "Entity 'Permissao' with identifier %d not found";

    private final String MSG_PERMISSAO_EM_USO =
            "Entity 'Permissao' with identifier %d is in use";

    private PermissaoRepository repository;


    public PermissaoService(PermissaoRepository repository) {
        this.repository = repository;
    }

    public Permissao findById(Long id){
        return repository.findById(id).orElseThrow(() ->
                new NegocioException(String.format(MSG_PERMISSAO_NAO_ENCONTRADA,id)));
    }

}
