package br.com.matheus.gerenciadordetreinamentos.domain.model;

import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct.AdministradorMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "ADMINISTRADORES")
public class Administrador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private Boolean ativo;

    public Administrador(Long id, String nome, String usuario, String senha, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Administrador() {
    }

    public AdministradorDTO buildDTO() {
        var dto = AdministradorMapper.INSTANCE.toDTO(this);
        dto.addWithSelfRel();
        return dto;
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
