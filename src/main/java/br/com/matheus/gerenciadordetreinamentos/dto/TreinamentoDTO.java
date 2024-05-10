package br.com.matheus.gerenciadordetreinamentos.dto;

import br.com.matheus.gerenciadordetreinamentos.controller.AdministradorController;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class TreinamentoDTO extends RepresentationModel<TreinamentoDTO> {

    private Long key;
    private String nome;
    private String descricao;
    private String codigo;
    private LocalDate data;
    private LocalTime abertura;
    private LocalTime encerramento;
    private LocalDateTime dataCadastro;
    private Boolean ativo;

    private List<Grupo> grupos;
    private List<Presenca> presencas;
    private Professor professor;

    public TreinamentoDTO(Long key, String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo, List<Grupo> grupos, List<Presenca> presencas, Professor professor) {
        this.key = key;
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.grupos = grupos;
        this.presencas = presencas;
        this.professor = professor;
    }

    public TreinamentoDTO() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalTime abertura) {
        this.abertura = abertura;
    }

    public LocalTime getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(LocalTime encerramento) {
        this.encerramento = encerramento;
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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Presenca> presencas) {
        this.presencas = presencas;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
