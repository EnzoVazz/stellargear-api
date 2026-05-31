package br.com.fiap.stellargear.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin_sistema")
public class AdminSistema extends UsuarioSistema {
    
    @Column(name = "setor_responsavel")
    private String setorResponsavel;
}