package br.com.fiap.stellargear.repository;

import br.com.fiap.stellargear.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}