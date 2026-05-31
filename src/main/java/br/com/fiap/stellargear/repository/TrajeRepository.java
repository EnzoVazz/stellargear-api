package br.com.fiap.stellargear.repository;

import br.com.fiap.stellargear.model.Traje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajeRepository extends JpaRepository<Traje, Long> {
}