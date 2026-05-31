package br.com.fiap.stellargear.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "autorizacao_medica")
public class AutorizacaoMedica {

    @EmbeddedId
    private AutorizacaoId id = new AutorizacaoId();

    @ManyToOne
    @MapsId("idMedico")
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @MapsId("idPassageiro")
    @JoinColumn(name = "id_passageiro")
    private Passageiro passageiro;

    @Column(name = "dt_autorizacao")
    private LocalDate dtAutorizacao;

    @Column(name = "status")
    private String status;
}