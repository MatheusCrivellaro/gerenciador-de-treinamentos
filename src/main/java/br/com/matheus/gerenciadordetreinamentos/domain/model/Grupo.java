package br.com.matheus.gerenciadordetreinamentos.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GRUPOS")
public class Grupo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;

    @ManyToMany(mappedBy = "grupos")
    private List<Funcionario> funcionarios;

    @ManyToMany(mappedBy = "grupos")
    private List<Treinamento> treinamentos;

    public Grupo(Long id, String nome, String descricao, Boolean ativo, List<Funcionario> funcionarios, List<Treinamento> treinamentos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.funcionarios = funcionarios;
        this.treinamentos = treinamentos;
    }

    public Grupo(String nome, String descricao, Boolean ativo, List<Funcionario> funcionarios, List<Treinamento> treinamentos) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.funcionarios = funcionarios;
        this.treinamentos = treinamentos;
    }

    public Grupo(String nome, String descricao, Boolean ativo, List<Funcionario> funcionarios) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.funcionarios = funcionarios;
    }

    public Grupo(Long id, String nome, String descricao, List<Funcionario> funcionarios, List<Treinamento> treinamentos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.funcionarios = funcionarios;
        this.treinamentos = treinamentos;
    }

    public Grupo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
