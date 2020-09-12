package com.api.algafood.controller;

import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.model.Cidade;
import com.api.algafood.domain.repository.CidadeRepository;
import com.api.algafood.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository repository;
    private CidadeService service;

    public CidadeController(CidadeRepository repository, CidadeService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public List<Cidade> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){
        Optional<Cidade> cidade = repository.findById(id);
            return (cidade.isPresent()) ? ResponseEntity.ok(cidade.get()) : ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cidade cidade){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cidade));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        try {
            service.remove(id);
           return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
