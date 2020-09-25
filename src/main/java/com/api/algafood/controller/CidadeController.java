package com.api.algafood.controller;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository repository;
    private CidadeService service;


    public CidadeController(CidadeRepository repository,
                            CidadeService service
    ) {
        this.repository = repository;
        this.service = service;

    }

    @GetMapping
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade save(@RequestBody Cidade cidade) {
        try {
            return service.save(cidade);
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
