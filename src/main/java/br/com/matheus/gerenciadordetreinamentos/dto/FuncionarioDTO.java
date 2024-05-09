package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.AdministradorController;
import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FuncionarioDTO extends RepresentationModel<FuncionarioDTO> {

    private Long key;
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String telefone;
    private String cpf;
    private Genero genero;
    private LocalDate dataNascimento;
    private LocalDateTime dataRegistro;
    private boolean ativo;

    public FuncionarioDTO(Long key, String nome, String email, String usuario, String senha, String telefone, String cpf, Genero genero, LocalDate dataNascimento, LocalDateTime dataRegistro, boolean ativo) {
        this.key = key;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.dataRegistro = dataRegistro;
        this.ativo = ativo;
    }

    public FuncionarioDTO(String nome, String email, String usuario, String senha, String telefone, String cpf, Genero genero, LocalDate dataNascimento, LocalDateTime dataRegistro, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.dataRegistro = dataRegistro;
        this.ativo = ativo;
    }

    public FuncionarioDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
