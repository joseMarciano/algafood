package com.api.algafood.domain.repository.restaurante;


import com.api.algafood.domain.model.Restaurante;
import com.api.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
                                                RestauranteRepositoryQueries,
                                                JpaSpecificationExecutor {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal valorMenor, BigDecimal valorMaior);

}
