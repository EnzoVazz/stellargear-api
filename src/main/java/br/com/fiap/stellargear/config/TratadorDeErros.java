package br.com.fiap.stellargear.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacaoDTO>> tratarErro400(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        
        List<ErroValidacaoDTO> errosFormatados = erros.stream()
                .map(ErroValidacaoDTO::new)
                .collect(Collectors.toList());
                
        return ResponseEntity.badRequest().body(errosFormatados);
    }

    public record ErroValidacaoDTO(String campo, String mensagem) {
        public ErroValidacaoDTO(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}