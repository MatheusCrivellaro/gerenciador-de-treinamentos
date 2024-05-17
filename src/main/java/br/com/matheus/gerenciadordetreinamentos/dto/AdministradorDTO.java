package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.AdministradorController;
import br.com.matheus.gerenciadordetreinamentos.dto.view.AdministradorViewDTO;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class AdministradorDTO extends RepresentationModel<AdministradorDTO> {

    private Long key;
    private String nome;
    private String usuario;
    private String senha;
    private Boolean ativo;

    public AdministradorDTO(Long key, String nome, String usuario, String senha, Boolean ativo) {
        this.key = key;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public AdministradorDTO() {
    }

    public void addWithSelfRel() {
        this.add(linkTo(methodOn(AdministradorController.class).findById(this.getKey())).withSelfRel());
    }

    public AdministradorViewDTO toView() {
        return new AdministradorViewDTO(
                this.key,
                this.nome,
                this.usuario
        );
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
