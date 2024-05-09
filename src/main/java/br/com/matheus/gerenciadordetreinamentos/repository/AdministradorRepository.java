package br.com.matheus.gerenciadordetreinamentos.repository;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    List<Administrador> findAllByAtivoTrue();

    List<Administrador> findByNomeContainingAndAtivoTrue(String nome);

    List<Administrador> findByUsuarioContainingAndAtivoTrue(String nome);

    Optional<Administrador> findByIdAndAtivoTrue(Long id);

}
