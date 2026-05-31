package br.com.fiap.stellargear.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CRM é obrigatório")
        String crm,

        @NotBlank(message = "A especialidade é obrigatória")
        String especialidade
) {}