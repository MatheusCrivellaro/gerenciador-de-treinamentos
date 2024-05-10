package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.GrupoController;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import jakarta.persistence.ManyToMany;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GrupoDTO extends RepresentationModel<GrupoDTO> {

    private Long key;
    private String nome;
    private String descricao;
    private Boolean ativo;

    private List<Funcionario> funcionarios;
    private List<Treinamento> treinamentos;

    public GrupoDTO(Long key, String nome, String descricao, Boolean ativo, List<Funcionario> funcionarios, List<Treinamento> treinamentos) {
        this.key = key;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.funcionarios = funcionarios;
        this.treinamentos = treinamentos;
    }

    public GrupoDTO() {
    }

    public void addWithSelfRel() {
        this.add(linkTo(methodOn(GrupoController.class).findById(this.getKey())).withSelfRel());
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Treinamento> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<Treinamento> treinamentos) {
        this.treinamentos = treinamentos;
    }
}
