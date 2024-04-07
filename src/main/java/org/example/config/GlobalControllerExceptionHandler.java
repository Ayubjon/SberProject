package org.example.config;

import org.example.model.response.WrongParameterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private final String EXAMPLE = "{\"factorial_num\": 100}";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WrongParameterResponse> handleBadRequest(ResponseStatusException e) {
        WrongParameterResponse response = new WrongParameterResponse(HttpStatus.BAD_REQUEST, e.getMessage(), EXAMPLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WrongParameterResponse> handleBadRequest(HttpMessageNotReadableException e) {
    WrongParameterResponse response = new WrongParameterResponse(HttpStatus.BAD_REQUEST, e.getMessage(), EXAMPLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WrongParameterResponse> handleBadRequest(MethodArgumentNotValidException e) {
        WrongParameterResponse response = new WrongParameterResponse(HttpStatus.BAD_REQUEST, e.getMessage(), EXAMPLE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
