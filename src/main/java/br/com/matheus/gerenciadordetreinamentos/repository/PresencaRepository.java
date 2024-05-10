package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
}
