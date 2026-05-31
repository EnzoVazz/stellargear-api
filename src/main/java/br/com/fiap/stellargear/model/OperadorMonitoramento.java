package br.com.fiap.stellargear.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "operador_monitoramento")
public class OperadorMonitoramento extends UsuarioSistema {
    
    @Column(name = "turno_trabalho")
    private String turnoTrabalho;
}