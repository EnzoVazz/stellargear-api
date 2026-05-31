package br.com.fiap.stellargear.controller;

import br.com.fiap.stellargear.dto.LeituraSensorRequestDTO;
import br.com.fiap.stellargear.dto.LeituraSensorResponseDTO;
import br.com.fiap.stellargear.service.LeituraSensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leituras")
@Tag(name = "Monitoramento IoT (Sensores)", description = "Recepção e listagem de sinais vitais contínuos (temperatura, batimentos, humidade) enviados pelos trajes via hardware.")
public class LeituraSensorController {

    @Autowired
    private LeituraSensorService service;

    @PostMapping
    @Operation(summary = "Registrar Leitura (ESP32)", description = "Recebe os dados brutos de telemetria do hardware e vincula ao traje do passageiro de forma imutável.")
    public ResponseEntity<EntityModel<LeituraSensorResponseDTO>> registrarLeitura(@RequestBody @Valid LeituraSensorRequestDTO dto) {
        LeituraSensorResponseDTO response = service.registrarLeitura(dto);

        EntityModel<LeituraSensorResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LeituraSensorController.class).listarTodas()).withRel("listar-todas"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar Histórico de Leituras", description = "Retorna a lista completa de todos os logs de sinais vitais captados pelos sensores no sistema.")
    public ResponseEntity<List<EntityModel<LeituraSensorResponseDTO>>> listarTodas() {
        List<EntityModel<LeituraSensorResponseDTO>> response = service.listarTodas().stream()
                .map(leitura -> {
                    EntityModel<LeituraSensorResponseDTO> resource = EntityModel.of(leitura);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LeituraSensorController.class).listarTodas()).withRel("listar-todas"));
                    return resource;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}