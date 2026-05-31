package br.com.fiap.stellargear.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

public record PassageiroRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF(message = "Formato de CPF inválido") 
        String cpf,

        @NotNull(message = "A idade é obrigatória")
        @Positive(message = "A idade deve ser um número positivo")
        Integer idade,

        @NotBlank(message = "O status médico é obrigatório (ex: APTO, EM AVALIACAO)")
        String statusMedico,

        @Valid 
        @NotNull(message = "O endereço é obrigatório")
        EnderecoDTO endereco
) {}