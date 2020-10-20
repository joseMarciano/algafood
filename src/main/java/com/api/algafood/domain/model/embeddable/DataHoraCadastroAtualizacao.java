package com.api.algafood.domain.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Embeddable
public class DataHoraCadastroAtualizacao {

    @Column(name = "DATA_ATUALIZACAO")
    private OffsetDateTime dataAtualizacao;

    @Column(name = "DATA_CADASTRO")
    private OffsetDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = OffsetDateTime.now();
        this.dataAtualizacao = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = OffsetDateTime.now();
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
