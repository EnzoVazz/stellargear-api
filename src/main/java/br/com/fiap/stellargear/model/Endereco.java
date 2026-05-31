package br.com.fiap.stellargear.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "logradouro")
    private String logradouro;
    
    @Column(name = "numero")
    private String numero;
}