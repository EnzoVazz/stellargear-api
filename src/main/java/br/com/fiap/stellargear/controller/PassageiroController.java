package br.com.fiap.stellargear.controller;

import br.com.fiap.stellargear.dto.PassageiroRequestDTO;
import br.com.fiap.stellargear.dto.PassageiroResponseDTO;
import br.com.fiap.stellargear.service.PassageiroService;
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
@RequestMapping("/passageiros")
@Tag(name = "Passageiros", description = "Gerenciamento dos tripulantes/passageiros monitorados pelo sistema e seus status de saúde.")
public class PassageiroController {

    @Autowired
    private PassageiroService service;

    @PostMapping
    @Operation(summary = "Cadastrar Passageiro", description = "Registra um novo passageiro na plataforma.")
    public ResponseEntity<EntityModel<PassageiroResponseDTO>> criar(@RequestBody @Valid PassageiroRequestDTO dto) {
        PassageiroResponseDTO response = service.criar(dto);
        
        EntityModel<PassageiroResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassageiroController.class).listarTodos()).withRel("listar-todos"));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar Passageiros", description = "Retorna a lista de todos os passageiros.")
    public ResponseEntity<List<EntityModel<PassageiroResponseDTO>>> listarTodos() {
        List<EntityModel<PassageiroResponseDTO>> response = service.listarTodos().stream()
                .map(passageiro -> {
                    EntityModel<PassageiroResponseDTO> resource = EntityModel.of(passageiro);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassageiroController.class).listarTodos()).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Passageiro por ID", description = "Consulta os dados detalhados de um passageiro específico.")
    public ResponseEntity<EntityModel<PassageiroResponseDTO>> buscarPorId(@PathVariable Long id) {
        PassageiroResponseDTO response = service.buscarPorId(id);
        
        EntityModel<PassageiroResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassageiroController.class).buscarPorId(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassageiroController.class).listarTodos()).withRel("listar-todos"));
        
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Passageiro", description = "Altera os dados cadastrais de um passageiro.")
    public ResponseEntity<EntityModel<PassageiroResponseDTO>> atualizar(@PathVariable Long id, @RequestBody @Valid PassageiroRequestDTO dto) {
        PassageiroResponseDTO response = service.atualizar(id, dto);
        
        EntityModel<PassageiroResponseDTO> resource = EntityModel.of(response);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassageiroController.class).buscarPorId(id)).withSelfRel());
        
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Passageiro", description = "Remove um passageiro da base de dados.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}