package com.example.flightreservation.aop;


import com.example.flightreservation.exceptions.EntityNotFoundException;
import com.example.flightreservation.exceptions.FundsException;
import com.example.flightreservation.model.responce.MessageResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(MessageResponse.sendMessage(ex.getMessage()));
    }

    @ExceptionHandler(FundsException.class)
    protected ResponseEntity<?> handleFundsException(FundsException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(MessageResponse.sendMessage(ex.getMessage()));
    }
}