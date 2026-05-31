package br.com.fiap.stellargear.dto;

public record MedicoResponseDTO(
        Long idMedico,
        String nome,
        String crm,
        String especialidade
) {}