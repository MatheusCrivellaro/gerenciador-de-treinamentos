package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.AdministradorController;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PresencaDTO extends RepresentationModel<PresencaDTO> {

    private Long key;
    private LocalDateTime dataPublicacao;
    private Boolean presente;

    public PresencaDTO(Long key, LocalDateTime dataPublicacao, Boolean presente) {
        this.key = key;
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
    }

    public PresencaDTO(LocalDateTime dataPublicacao, Boolean presente) {
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
    }

    public PresencaDTO() {
    }

    public void addWithSelfRel() {
        this.add(linkTo(methodOn(AdministradorController.class).findById(this.getKey())).withSelfRel());
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
