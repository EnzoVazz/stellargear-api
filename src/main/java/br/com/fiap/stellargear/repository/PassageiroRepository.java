package br.com.fiap.stellargear.repository;

import br.com.fiap.stellargear.model.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
}