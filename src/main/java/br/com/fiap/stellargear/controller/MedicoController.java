package br.com.fiap.stellargear.controller;

import br.com.fiap.stellargear.dto.MedicoRequestDTO;
import br.com.fiap.stellargear.dto.MedicoResponseDTO;
import br.com.fiap.stellargear.service.MedicoService;
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
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Endpoints para gerenciamento do corpo clínico e suas respectivas especialidades.")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Operation(summary = "Cadastrar Médico", description = "Registra um novo profissional de saúde no sistema validando seu CRM.")
    public ResponseEntity<EntityModel<MedicoResponseDTO>> criar(@RequestBody @Valid MedicoRequestDTO dto) {
        MedicoResponseDTO response = service.criar(dto);

        EntityModel<MedicoResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).listarTodos()).withRel("listar-todos"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar Médicos", description = "Retorna a lista completa de todos os médicos cadastrados na base de dados.")
    public ResponseEntity<List<EntityModel<MedicoResponseDTO>>> listarTodos() {
        List<EntityModel<MedicoResponseDTO>> response = service.listarTodos().stream()
                .map(medico -> {
                    EntityModel<MedicoResponseDTO> resource = EntityModel.of(medico);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).buscarPorId(medico.idMedico())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Médico por ID", description = "Busca os detalhes de um médico específico através do seu ID de registro.")
    public ResponseEntity<EntityModel<MedicoResponseDTO>> buscarPorId(@PathVariable Long id) {
        MedicoResponseDTO response = service.buscarPorId(id);

        EntityModel<MedicoResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).buscarPorId(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).listarTodos()).withRel("listar-todos"));

        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Médico", description = "Atualiza os dados cadastrais (nome, CRM ou especialidade) de um médico existente.")
    public ResponseEntity<EntityModel<MedicoResponseDTO>> atualizar(@PathVariable Long id, @RequestBody @Valid MedicoRequestDTO dto) {
        MedicoResponseDTO response = service.atualizar(id, dto);

        EntityModel<MedicoResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).buscarPorId(id)).withSelfRel());

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Médico", description = "Remove um profissional do sistema com base no ID fornecido.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}