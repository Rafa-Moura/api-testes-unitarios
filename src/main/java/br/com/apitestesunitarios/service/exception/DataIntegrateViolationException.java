package br.com.apitestesunitarios.service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataIntegrateViolationException extends RuntimeException {
    public DataIntegrateViolationException(String message) {
        super(message);
        log.error(message);
    }
}
