package br.com.matheus.gerenciadordetreinamentos.domain.model;

import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct.PresencaMapper;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRESENCAS")
public class Presenca {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;
    private Boolean presente;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Treinamento treinamento;

    public Presenca(Long id, LocalDateTime dataPublicacao, Boolean presente, Funcionario funcionario, Treinamento treinamento) {
        this.id = id;
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
        this.funcionario = funcionario;
        this.treinamento = treinamento;
    }

    public Presenca(LocalDateTime dataPublicacao, Boolean presente, Funcionario funcionario, Treinamento treinamento) {
        this.dataPublicacao = dataPublicacao;
        this.presente = presente;
        this.funcionario = funcionario;
        this.treinamento = treinamento;
    }

    public Presenca() {
    }

    public PresencaDTO buildDTO() {
        var dto = PresencaMapper.INSTANCE.toDTO(this);
        dto.addWithSelfRel();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
