package br.com.fiap.stellargear.service;

import br.com.fiap.stellargear.dto.EnderecoDTO;
import br.com.fiap.stellargear.dto.PassageiroRequestDTO;
import br.com.fiap.stellargear.dto.PassageiroResponseDTO;
import br.com.fiap.stellargear.model.Endereco;
import br.com.fiap.stellargear.model.Passageiro;
import br.com.fiap.stellargear.repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;

    public PassageiroResponseDTO criar(PassageiroRequestDTO dto) {
        Passageiro passageiro = new Passageiro();
        passageiro.setNome(dto.nome());
        passageiro.setCpf(dto.cpf());
        passageiro.setIdade(dto.idade());
        passageiro.setStatusMedico(dto.statusMedico());

        Endereco endereco = new Endereco();
        endereco.setCep(dto.endereco().cep());
        endereco.setLogradouro(dto.endereco().logradouro());
        endereco.setNumero(dto.endereco().numero());
        passageiro.setEndereco(endereco);

        Passageiro salvo = repository.save(passageiro);

        return mapearParaResponse(salvo);
    }

    public List<PassageiroResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }
    
    public PassageiroResponseDTO buscarPorId(Long id) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado com o ID: " + id));
        return mapearParaResponse(passageiro);
    }

    public PassageiroResponseDTO atualizar(Long id, PassageiroRequestDTO dto) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado com o ID: " + id));

        passageiro.setNome(dto.nome());
        passageiro.setCpf(dto.cpf());
        passageiro.setIdade(dto.idade());
        passageiro.setStatusMedico(dto.statusMedico());

        passageiro.getEndereco().setCep(dto.endereco().cep());
        passageiro.getEndereco().setLogradouro(dto.endereco().logradouro());
        passageiro.getEndereco().setNumero(dto.endereco().numero());

        Passageiro salvo = repository.save(passageiro);
        return mapearParaResponse(salvo);
    }

    public void excluir(Long id) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado com o ID: " + id));
        repository.delete(passageiro);
    }

    private PassageiroResponseDTO mapearParaResponse(Passageiro passageiro) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                passageiro.getEndereco().getCep(),
                passageiro.getEndereco().getLogradouro(),
                passageiro.getEndereco().getNumero()
        );
        
        return new PassageiroResponseDTO(
                passageiro.getIdPassageiro(),
                passageiro.getNome(),
                passageiro.getCpf(),
                passageiro.getIdade(),
                passageiro.getStatusMedico(),
                enderecoDTO
        );
    }
}