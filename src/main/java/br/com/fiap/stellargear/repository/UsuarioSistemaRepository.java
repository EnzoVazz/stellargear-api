package br.com.fiap.stellargear.repository;

import br.com.fiap.stellargear.model.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Long> {
    
    UserDetails findByEmail(String email);
    
}