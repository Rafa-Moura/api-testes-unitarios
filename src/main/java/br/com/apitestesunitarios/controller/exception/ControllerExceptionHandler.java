package br.com.apitestesunitarios.controller.exception;

import br.com.apitestesunitarios.service.exception.DataIntegrityViolationException;
import br.com.apitestesunitarios.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {

        StandardError standardError = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrateViolation(DataIntegrityViolationException exception, HttpServletRequest request) {

        StandardError standardError = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException exception,
                                                                      HttpServletRequest request) {

        StandardError standardError = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getClass().getName())
                .path(request.getRequestURI())
                .constraintViolations(exception.getConstraintViolations()
                        .stream()
                        .map(x -> x.getMessageTemplate())
                        .collect(Collectors.toList()))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}