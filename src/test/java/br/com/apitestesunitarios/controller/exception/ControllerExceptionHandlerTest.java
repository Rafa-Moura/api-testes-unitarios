package br.com.apitestesunitarios.controller.exception;

import br.com.apitestesunitarios.service.exception.DataIntegrityViolationException;
import br.com.apitestesunitarios.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

class ControllerExceptionHandlerTest {

    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";

    @Mock
    ObjectNotFoundException objectNotFoundException;

    @Mock
    DataIntegrityViolationException dataIntegrityViolationException;

    private ControllerExceptionHandler controllerExceptionHandler;

    private LocalDateTime dateTime;

    @BeforeEach
    void setUp() {
        objectNotFoundException = new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO);

        dataIntegrityViolationException = new DataIntegrityViolationException(EMAIL_JA_CADASTRADO);

        controllerExceptionHandler = new ControllerExceptionHandler();

        createAMockDate();

    }

    @Test
    void objectNotFound() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        StandardError standardError = controllerExceptionHandler
                .ObjectNotFound(objectNotFoundException, request).getBody();


        Assertions.assertNotNull(standardError);
        Assertions.assertEquals(404, standardError.getStatus());
        Assertions.assertTrue(dateTime.isBefore(standardError.getTimestamp()) || dateTime.isEqual(standardError.getTimestamp()));
        Assertions.assertEquals(request.getRequestURI(), standardError.getPath());
        Assertions.assertEquals(USUARIO_NAO_ENCONTRADO, standardError.getError());
    }

    @Test
    void dataIntegrateViolation() {

        HttpServletRequest request = mock(HttpServletRequest.class);

        StandardError standardError = controllerExceptionHandler
                .dataIntegrateViolation(dataIntegrityViolationException, request).getBody();


        Assertions.assertNotNull(standardError);
        Assertions.assertEquals(409, standardError.getStatus());
        Assertions.assertTrue(dateTime.isBefore(standardError.getTimestamp()) || dateTime.isEqual(standardError.getTimestamp()));
        Assertions.assertEquals(request.getRequestURI(), standardError.getPath());
        Assertions.assertEquals(EMAIL_JA_CADASTRADO, standardError.getError());
    }

    void createAMockDate() {
        dateTime = LocalDateTime.now();
    }

}