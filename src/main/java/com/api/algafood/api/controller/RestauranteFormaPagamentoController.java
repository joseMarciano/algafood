package com.api.algafood.api.controller;

import com.api.algafood.api.assembler.Converter;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoCompleta;
import com.api.algafood.api.model.representation.formapagamento.FormaPagamentoCompletaListagem;
import com.api.algafood.domain.model.FormaPagamento;
import com.api.algafood.domain.service.FormaPagamentoService;
import com.api.algafood.domain.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    private RestauranteService restauranteService;
    private FormaPagamentoService formaPagamentoService;
    private Converter<FormaPagamento,FormaPagamentoCompletaListagem, FormaPagamentoCompleta> assemblers;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService,
                                               FormaPagamentoService formaPagamentoService,
                                               Converter<FormaPagamento, FormaPagamentoCompletaListagem, FormaPagamentoCompleta> assemblers) {
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.assemblers = assemblers;
    }

    @GetMapping
    public List<FormaPagamentoCompletaListagem> listAll(@PathVariable Long restauranteId){
        var restaurante = restauranteService.findById(restauranteId);
        return assemblers.toCollectionDTO(restaurante.getFormasPagamento(),FormaPagamentoCompletaListagem.class);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFormaPagamentoDeRestaurante(@PathVariable Long restauranteId,
                                                   @PathVariable Long formaPagamentoId){
        restauranteService.desassociarFormaPagamento(restauranteId,formaPagamentoId);
    }

    @PutMapping("/{formaPagamentoId}")
    public List<FormaPagamentoCompletaListagem> associarFormaPagamentoDeRestaurante(@PathVariable Long restauranteId,
                                                                                    @PathVariable Long formaPagamentoId){
        var formasPagamentoList =
                restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return assemblers.toCollectionDTO(formasPagamentoList,FormaPagamentoCompletaListagem.class);
    }


}
