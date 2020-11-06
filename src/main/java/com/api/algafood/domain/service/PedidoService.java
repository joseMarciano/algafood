package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Pedido;
import com.api.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private static final String MSG_PEDIDO_EM_USO
            = "Entity 'Pedido' with identifier %d is in use";

    private static final String MSG_PEDIDO_NAO_ENCONTRADO
            = "Entity 'Pedido' with identifier %d not found";

    private PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_NAO_ENCONTRADO,id)));
    }
}
