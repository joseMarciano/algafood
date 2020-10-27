package com.api.algafood.domain.service;


import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Entity 'Cidade' with identifier %d is in use";

    private static final String MSG_CIDADE_NAO_ENCONTRADA
            = "Entity 'Cidade' with identifier %d not found";

    private CidadeRepository repository;
    private EstadoService estadoService;


    public CidadeService(CidadeRepository repository,
                         EstadoRepository estadoRepository, EstadoService estadoService) {
        this.repository = repository;
        this.estadoService = estadoService;
    }

    @Transactional
    public Cidade save(Cidade cidade) {
        estadoService.findById(cidade.getEstado().getId());
        return repository.save(cidade);
    }

    @Transactional
    public void remove(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }
    }

    public Cidade findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Entity 'Cidade' with identifier %d not found", id)));
    }
}

