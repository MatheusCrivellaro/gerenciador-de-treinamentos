package br.com.matheus.gerenciadordetreinamentos.dto;

import org.springframework.hateoas.RepresentationModel;

public class AdministradorDTO extends RepresentationModel<AdministradorDTO> {

    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private Boolean ativo;

    public AdministradorDTO(Long id, String nome, String usuario, String senha, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public AdministradorDTO(String nome, String usuario, String senha, Boolean ativo) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public AdministradorDTO() {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
