package br.com.matheus.gerenciadordetreinamentos.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public TreinamentoDTO(Long key, String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo) {
        this.key = key;
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public TreinamentoDTO(String nome, String descricao, String codigo, LocalDate data, LocalTime abertura, LocalTime encerramento, LocalDateTime dataCadastro, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.data = data;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public TreinamentoDTO() {
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
}
