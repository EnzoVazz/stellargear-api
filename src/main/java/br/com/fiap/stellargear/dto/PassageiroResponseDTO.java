package br.com.fiap.stellargear.dto;

public record PassageiroResponseDTO(
        Long idPassageiro,
        String nome,
        String cpf,
        Integer idade,
        String statusMedico,
        EnderecoDTO endereco
) {}