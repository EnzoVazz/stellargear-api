package br.com.fiap.stellargear.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "traje")
public class Traje {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_traje_gen")
    @SequenceGenerator(name = "seq_traje_gen", sequenceName = "seq_traje", allocationSize = 1)
    @Column(name = "id_traje")
    private Long idTraje;

    @ManyToOne
    @JoinColumn(name = "id_passageiro", nullable = false)
    private Passageiro passageiro;

    @Column(name = "codigo_rfid", unique = true)
    private String codigoRfid;

    @Column(name = "dt_alocacao")
    private LocalDate dtAlocacao;
}