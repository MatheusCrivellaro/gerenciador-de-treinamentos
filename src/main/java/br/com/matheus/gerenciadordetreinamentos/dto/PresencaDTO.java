package br.com.matheus.gerenciadordetreinamentos.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class PresencaDTO extends RepresentationModel<PresencaDTO> {

    private Long id;
    private LocalDateTime dataPublicacao;
    private Boolean presente;

    public PresencaDTO(Long id, LocalDateTime dataPublicacao, Boolean presente) {
        this.id = id;
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
    }

    public PresencaDTO(LocalDateTime dataPublicacao, Boolean presente) {
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
    }

    public PresencaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }
}
