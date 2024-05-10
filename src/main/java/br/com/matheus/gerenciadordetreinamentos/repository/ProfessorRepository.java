package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findAllByAtivoTrue();

    List<Professor> findByNomeContainingAndAtivoTrue(String nome);

    List<Professor> findByUsuarioContainingAndAtivoTrue(String usuario);

    List<Professor> findByEmailContainingAndAtivoTrue(String email);

    Optional<Professor> findByTelefoneAndAtivoTrue(String telefone);

    Optional<Professor> findByIdAndAtivoTrue(Long id);
}
