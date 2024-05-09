package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    List<Funcionario> findAllByAtivoTrue();

    List<Funcionario> findByNomeContainingAndAtivoTrue(String nome);

    List<Funcionario> findByUsuarioContainingAndAtivoTrue(String usuario);

    Optional<Funcionario> findByIdAndAtivoTrue(Long id);
}
