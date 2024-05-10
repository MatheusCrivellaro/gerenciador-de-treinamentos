package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.PresencaController;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import jakarta.persistence.ManyToOne;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PresencaDTO extends RepresentationModel<PresencaDTO> {

    private Long key;
    private LocalDateTime dataPublicacao;
    private Boolean presente;

    private Funcionario funcionario;
    private Treinamento treinamento;

    public PresencaDTO(Long key, LocalDateTime dataPublicacao, Boolean presente, Funcionario funcionario, Treinamento treinamento) {
        this.key = key;
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
        this.funcionario = funcionario;
        this.treinamento = treinamento;
    }

    public PresencaDTO() {
    }

    public void addWithSelfRel() {
        this.add(linkTo(methodOn(PresencaController.class).findById(this.getKey())).withSelfRel());
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
