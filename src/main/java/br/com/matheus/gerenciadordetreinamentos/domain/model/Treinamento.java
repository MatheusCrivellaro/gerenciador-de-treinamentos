package br.com.matheus.gerenciadordetreinamentos.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "TREINAMENTOS")
public class Treinamento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String codigo;
    private LocalDate data;
    private LocalTime abertura;
    private LocalTime encerramento;
    private LocalDateTime dataCadastro;
    private Boolean ativo;

    @ManyToOne
    private Professor professor;

    @ManyToMany
    @JoinTable(name = "treinamento_grupo", joinColumns = @JoinColumn(name = "id_treinamento"), inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos;

    @OneToMany
    @JoinTable(name = "treinamento_presenca", joinColumns = @JoinColumn(name = "id_treinamento"), inverseJoinColumns = @JoinColumn(name = "id_presenca"))
    private List<Presenca> presencas;

    public Treinamento(Long id, String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo, Professor professor, List<Grupo> grupos, List<Presenca> presencas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.professor = professor;
        this.grupos = grupos;
        this.presencas = presencas;
    }

    public Treinamento(String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo, Professor professor, List<Grupo> grupos, List<Presenca> presencas) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.professor = professor;
        this.grupos = grupos;
        this.presencas = presencas;
    }

    public Treinamento(String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo, List<Grupo> grupos, Professor professor) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.grupos = grupos;
        this.professor = professor;
    }


    public Treinamento() {
    }

    public Treinamento(Long id, String nome, String descricao, LocalDate data, LocalTime abertura, LocalTime encerramento, List<Grupo> grupos, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.grupos = grupos;
        this.professor = professor;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
}
