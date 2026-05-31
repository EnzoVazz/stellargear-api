package br.com.fiap.stellargear.dto;

import java.time.LocalDateTime;

public record LeituraSensorResponseDTO(
        Long idLeitura,
        Long idTraje,
        Double temperatura,
        Double humidade,
        Double batimentos,
        LocalDateTime dtLeitura
) {}