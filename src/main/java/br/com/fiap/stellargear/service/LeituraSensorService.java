package br.com.fiap.stellargear.service;

import br.com.fiap.stellargear.dto.LeituraSensorRequestDTO;
import br.com.fiap.stellargear.dto.LeituraSensorResponseDTO;
import br.com.fiap.stellargear.model.LeituraSensor;
import br.com.fiap.stellargear.model.Traje;
import br.com.fiap.stellargear.repository.LeituraSensorRepository;
import br.com.fiap.stellargear.repository.TrajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeituraSensorService {

    @Autowired
    private LeituraSensorRepository leituraRepository;

    @Autowired
    private TrajeRepository trajeRepository;

    public LeituraSensorResponseDTO registrarLeitura(LeituraSensorRequestDTO dto) {
        Traje traje = trajeRepository.findById(dto.idTraje())
                .orElseThrow(() -> new RuntimeException("Traje não encontrado com o ID: " + dto.idTraje()));

        LeituraSensor leitura = new LeituraSensor();
        leitura.setTraje(traje);
        leitura.setTemperatura(dto.temperatura());
        leitura.setHumidade(dto.humidade());
        leitura.setBatimentos(dto.batimentos());
        leitura.setDtLeitura(LocalDateTime.now()); 

        LeituraSensor salvo = leituraRepository.save(leitura);

        return mapearParaResponse(salvo);
    }

    public List<LeituraSensorResponseDTO> listarTodas() {
        return leituraRepository.findAll().stream()
                .map(this::mapearParaResponse)
                .collect(Collectors.toList());
    }

    private LeituraSensorResponseDTO mapearParaResponse(LeituraSensor leitura) {
        return new LeituraSensorResponseDTO(
                leitura.getIdLeitura(),
                leitura.getTraje().getIdTraje(),
                leitura.getTemperatura(),
                leitura.getHumidade(),
                leitura.getBatimentos(),
                leitura.getDtLeitura()
        );
    }
}