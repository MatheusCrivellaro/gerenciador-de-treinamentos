package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
    List<Treinamento> findAllByAtivoTrue();
    Optional<Treinamento> findByIdAndAtivoTrue(Long id);
}
