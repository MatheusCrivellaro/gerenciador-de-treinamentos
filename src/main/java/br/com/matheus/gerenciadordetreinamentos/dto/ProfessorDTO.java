package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.ProfessorController;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.view.ProfessorViewDTO;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProfessorDTO extends RepresentationModel<ProfessorDTO> {

    private Long key;
    private String nome;
    private String usuario;
    private String senha;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;
    private Boolean ativo;

    private List<Treinamento> treinamentos;

    public ProfessorDTO(Long key, String nome, String usuario, String senha, String email, String telefone, LocalDate dataNascimento, LocalDateTime dataCadastro, Boolean ativo, List<Treinamento> treinamentos) {
        this.key = key;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.treinamentos = treinamentos;
    }

    public ProfessorDTO() {
    }

    public void addWithSelfRel() {
        this.add(linkTo(methodOn(ProfessorController.class).findById(this.getKey())).withSelfRel());
    }

    public ProfessorViewDTO toView() {
        return new ProfessorViewDTO(
                this.key,
                this.nome,
                this.usuario,
                this.email,
                this.telefone,
                this.dataNascimento,
                this.treinamentos.stream().map(Treinamento::getId).toList()
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
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

    public List<Treinamento> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<Treinamento> treinamentos) {
        this.treinamentos = treinamentos;
    }
}
