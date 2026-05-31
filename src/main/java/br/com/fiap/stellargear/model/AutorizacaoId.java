package br.com.fiap.stellargear.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class AutorizacaoId implements Serializable {
    
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_passageiro")
    private Long idPassageiro;
}