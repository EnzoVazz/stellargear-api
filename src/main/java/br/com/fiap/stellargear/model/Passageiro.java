package br.com.fiap.stellargear.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "passageiro")
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passageiro_seq")
    @SequenceGenerator(name = "passageiro_seq", sequenceName = "seq_passageiro", allocationSize = 1)
    @Column(name = "id_passageiro")
    private Long idPassageiro;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "idade")
    private Integer idade;
    
    @Column(name = "status_medico")
    private String statusMedico;

    @Embedded
    private Endereco endereco;
}