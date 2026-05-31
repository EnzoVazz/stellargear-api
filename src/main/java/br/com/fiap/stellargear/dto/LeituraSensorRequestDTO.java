package br.com.fiap.stellargear.dto;

import jakarta.validation.constraints.NotNull;

public record LeituraSensorRequestDTO(
        @NotNull(message = "O ID do traje é obrigatório")
        Long idTraje,
        Double temperatura,
        Double humidade,
        Double batimentos
) {}