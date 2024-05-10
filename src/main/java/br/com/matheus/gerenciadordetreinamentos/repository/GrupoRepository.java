package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<Grupo> findAllByAtivoTrue();

    List<Grupo> findByNomeContainingAndAtivoTrue(String nome);

    Optional<Grupo> findByIdAndAtivoTrue(Long id);
}
