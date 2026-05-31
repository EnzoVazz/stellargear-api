package br.com.fiap.stellargear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stellargear.dto.AutenticacaoDTO;
import br.com.fiap.stellargear.dto.TokenJwtDTO;
import br.com.fiap.stellargear.model.UsuarioSistema;
import br.com.fiap.stellargear.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Endpoints para gerenciamento de acesso e login no sistema.")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Efetuar Login", description = "Recebe o email e a senha do usuário, valida no banco de dados e retorna o Token JWT para acesso à API.")
    public ResponseEntity<TokenJwtDTO> efetuarLogin(@RequestBody @Valid AutenticacaoDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuarioSistema) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }
}