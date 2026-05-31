package br.com.fiap.stellargear.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @NotBlank(message = "O CEP é obrigatório")
        @Size(max = 10, message = "O CEP não pode ter mais de 10 caracteres")
        String cep,

        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "O número é obrigatório")
        String numero
) {}