package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends CustomJpaRepository<Pedido,Long> {

    @Query("SELECT p FROM Pedido p JOIN FETCH p.usuario JOIN FETCH p.restaurante r JOIN FETCH r.cozinha ")
    List<Pedido> findAll(); /*sobrescrevendo o método findAll do JPA.
                             Estamos otimizando a consulta para evitar
                             que o Hibernate faça selects desnecessários na hora
                             de consultar todos os pedidos (analisar select com o método
                             sobrescrito e analisar com o método do hibernate)*/

}
