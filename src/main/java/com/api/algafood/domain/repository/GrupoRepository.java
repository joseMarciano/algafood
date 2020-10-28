package com.api.algafood.domain.repository;

import com.api.algafood.domain.model.Grupo;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo,Long> {
}
