package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_NAO_ENCONTRADO =
            "Entity 'FormaPagamento' with identifier %d not found";

    private static final String MSG_FORMA_PAGAMENTO_EM_USO =
            "Entity 'FormaPagamento' with identifier %d is in use";

    private FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaPagamento findById(Long id) {
       return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_FORMA_PAGAMENTO_NAO_ENCONTRADO, id)));
    }

    @Transactional
    public void remove(Long id){
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_FORMA_PAGAMENTO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        }
    }

    @Transactional
    public FormaPagamento save(FormaPagamento formaPagamento){
        return repository.save(formaPagamento);
    }
}
