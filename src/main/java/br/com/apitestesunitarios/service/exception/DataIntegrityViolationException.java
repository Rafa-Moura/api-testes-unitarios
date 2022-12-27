package br.com.apitestesunitarios.service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataIntegrityViolationException extends RuntimeException {
    public DataIntegrityViolationException(String message) {
        super(message);
        log.error(message);
    }
}
