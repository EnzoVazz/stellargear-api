package br.com.fiap.stellargear.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico_seq")
    @SequenceGenerator(name = "medico_seq", sequenceName = "seq_medico", allocationSize = 1)
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "nome")
    private String nome;

    @Column(name = "crm")
    private String crm;

    @Column(name = "especialidade")
    private String especialidade;
}