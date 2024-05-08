package br.com.matheus.gerenciadordetreinamentos.domain.model;

import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String telefone;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;
    private boolean ativo;

    @ManyToMany
    @JoinTable(name = "funcionario_grupo", joinColumns = @JoinColumn(name = "id_funcionario"), inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos;

    @OneToMany
    @JoinTable(name = "funcionario_presenca", joinColumns = @JoinColumn(name = "id_funcionario"), inverseJoinColumns = @JoinColumn(name = "id_presenca"))
    private List<Presenca> presencas;

    public Funcionario(Long id, String nome, String email, String usuario, String senha, String telefone, String cpf, Genero genero, LocalDate dataNascimento, LocalDateTime dataRegistro, boolean ativo, List<Grupo> grupos, List<Presenca> presencas) {
        this.id = id;
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
        this.grupos = grupos;
        this.presencas = presencas;
    }

    public Funcionario(String nome, String email, String usuario, String senha, String telefone, String cpf, Genero genero, LocalDate dataNascimento, LocalDateTime dataRegistro, boolean ativo, List<Grupo> grupos, List<Presenca> presencas) {
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
        this.grupos = grupos;
        this.presencas = presencas;
    }

    public Funcionario() {
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
