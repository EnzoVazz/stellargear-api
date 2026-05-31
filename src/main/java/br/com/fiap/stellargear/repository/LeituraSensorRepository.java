package br.com.fiap.stellargear.repository;

import br.com.fiap.stellargear.model.LeituraSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {
}