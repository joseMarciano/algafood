package com.api.algafood.domain.model.embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.OffsetDateTime;

@Embeddable
public class DataHoraCadastroAtualizacao {

    @Column(name = "DATA_ATUALIZACAO")
    private OffsetDateTime dataAtualizacao;

    @Column(name = "DATA_CADASTRO")
    private OffsetDateTime dataCadastro;

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
