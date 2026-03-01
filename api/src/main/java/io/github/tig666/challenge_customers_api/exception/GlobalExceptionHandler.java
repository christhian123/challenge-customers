package io.github.tig666.challenge_customers_api.exception;

import io.github.tig666.challenge_customers_api.dto.generic.ResponseGenericDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ResponseGenericDTO> returnDomainException(DomainException exception) {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(exception.statusCode, exception.message);

        return ResponseEntity.ok(responseGenericDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGenericDTO> returnValidationException(MethodArgumentNotValidException exception) {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(400, "Los datos enviados no son validos.");
        responseGenericDTO.setResponseData(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.ok(responseGenericDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseGenericDTO> returnGenericException(Exception exception) {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(500, "Ha ocurrido un error durante la operación.");
        responseGenericDTO.setResponseData(exception.getMessage());
        return ResponseEntity.ok(responseGenericDTO);
    }
}
