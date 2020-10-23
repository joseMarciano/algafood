package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.cidade.CidadeCompleta;
import com.api.algafood.api.model.representation.cidade.CidadeListagemCompleta;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository repository;
    private CidadeService service;
    private Converter<Cidade,CidadeListagemCompleta, CidadeCompleta> assemblers;


    public CidadeController(CidadeRepository repository,
                            CidadeService service,
                            Converter<Cidade, CidadeListagemCompleta, CidadeCompleta> assemblers) {
        this.repository = repository;
        this.service = service;

        this.assemblers = assemblers;
    }

    @GetMapping
    public List<CidadeListagemCompleta> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(),CidadeListagemCompleta.class);
    }

    @GetMapping("/{id}")
    public CidadeListagemCompleta find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id),CidadeListagemCompleta.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeListagemCompleta save(@RequestBody @Valid CidadeCompleta cidadeCompleta) {
        try {
            var cidade = assemblers.toDomainObject(cidadeCompleta,Cidade.class);
            return assemblers.toDTO(service.save(cidade),CidadeListagemCompleta.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }

//    /*Quando a excessão for tratada, esse método é chamado e eu
//     *retorno o que eu quiser (Estou capturando também as exceptions
//     *causadas por EntidadeNaoEncontradaException... passando o throwable)
//     */
//    @ExceptionHandler(EntidadeNaoEncontradaException.class)
//    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
//        var problem = new Problema(LocalDateTime.now(),e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
//    }
//
//    @ExceptionHandler(NegocioException.class)
//    public ResponseEntity<?> handleNegocioException(NegocioException e){
//        var problem = new Problema(LocalDateTime.now(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
//    }


}
