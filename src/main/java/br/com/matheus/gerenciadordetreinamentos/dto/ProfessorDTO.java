package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.AdministradorController;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProfessorDTO extends RepresentationModel<ProfessorDTO> {

    private Long key;
    private String nome;
    private String usuario;
    private String senha;
    private String email;
    private String telefone;
    private LocalDateTime dataNascimento;
    private LocalDateTime dataCadastro;
    private Boolean ativo;

    public ProfessorDTO(Long key, String nome, String usuario, String senha, String email, String telefone, LocalDateTime dataNascimento, LocalDateTime dataCadastro, Boolean ativo) {
        this.key = key;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public ProfessorDTO(String nome, String usuario, String senha, String email, String telefone, LocalDateTime dataNascimento, LocalDateTime dataCadastro, Boolean ativo) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public ProfessorDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
