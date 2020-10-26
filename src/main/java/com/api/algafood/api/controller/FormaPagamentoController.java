package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoCompleta;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoCompletaListagem;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.repository.FormaPagamentoRepository;
import com.api.algafood.domain.service.FormaPagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private FormaPagamentoRepository repository;
    private FormaPagamentoService service;
    private ModelMapper modelMapper;
    private Converter<FormaPagamento, FormaPagamentoCompletaListagem, FormaPagamentoCompleta> assemblers;

    public FormaPagamentoController(FormaPagamentoRepository repository,
                                    FormaPagamentoService service,
                                    ModelMapper modelMapper,
                                    Converter<FormaPagamento, FormaPagamentoCompletaListagem, FormaPagamentoCompleta> assemblers) {
        this.repository = repository;
        this.service = service;
        this.modelMapper = modelMapper;
        this.assemblers = assemblers;
    }

    @GetMapping("/{id}")
    public FormaPagamentoCompletaListagem find(@PathVariable Long id) {
        return assemblers.toDTO(service.findById(id), FormaPagamentoCompletaListagem.class);
    }

    @GetMapping
    public List<FormaPagamentoCompletaListagem> findAll() {
        return assemblers.toCollectionDTO(repository.findAll(), FormaPagamentoCompletaListagem.class);
    }

    @PostMapping
    public FormaPagamentoCompletaListagem create(@RequestBody @Valid FormaPagamentoCompleta formaPagamentoCompleta) {
        var formaPagamento = assemblers.toDomainObject(formaPagamentoCompleta, FormaPagamento.class);
        return assemblers.toDTO(service.save(formaPagamento), FormaPagamentoCompletaListagem.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

    @PutMapping("/{id}")
    public FormaPagamentoCompletaListagem update(@PathVariable Long id,
                                                 @RequestBody FormaPagamentoCompleta formaPagamentoCompleta) {
        var formaPagamento = service.findById(id);
        copyToDomainObject(formaPagamentoCompleta,formaPagamento);

        return assemblers.toDTO(service.save(formaPagamento),FormaPagamentoCompletaListagem.class);
    }

    private void copyToDomainObject(FormaPagamentoCompleta formaPagamentoCompleta, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoCompleta, formaPagamento);
    }
}
