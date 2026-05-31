package br.com.fiap.stellargear.service;

import br.com.fiap.stellargear.dto.MedicoRequestDTO;
import br.com.fiap.stellargear.dto.MedicoResponseDTO;
import br.com.fiap.stellargear.model.Medico;
import br.com.fiap.stellargear.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoResponseDTO criar(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());
        medico.setEspecialidade(dto.especialidade());

        Medico salvo = repository.save(medico);
        return mapearParaResponse(salvo);
    }

    public List<MedicoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));
        return mapearParaResponse(medico);
    }

    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));

        medico.setNome(dto.nome());
        medico.setCrm(dto.crm());
        medico.setEspecialidade(dto.especialidade());

        Medico salvo = repository.save(medico);
        return mapearParaResponse(salvo);
    }

    public void excluir(Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));
        repository.delete(medico);
    }

    private MedicoResponseDTO mapearParaResponse(Medico medico) {
        return new MedicoResponseDTO(
                medico.getIdMedico(), 
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}