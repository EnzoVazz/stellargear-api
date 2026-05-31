package br.com.fiap.stellargear.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "leitura_sensor")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leitura_gen")
    @SequenceGenerator(name = "seq_leitura_gen", sequenceName = "seq_leitura", allocationSize = 1)
    @Column(name = "id_leitura")
    private Long idLeitura;

    @ManyToOne
    @JoinColumn(name = "id_traje", nullable = false)
    private Traje traje;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "humidade")
    private Double humidade;

    @Column(name = "batimentos")
    private Double batimentos;

    @Column(name = "dt_leitura")
    private LocalDateTime dtLeitura;
}