package br.com.apitestesunitarios.service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String classError, String message) {
        super(message);
        log.error(classError + " " + message);
    }
}
