package com.alekseiko.laas;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ResponseStatus(
            value = HttpStatus.GATEWAY_TIMEOUT,
            reason = "Operation is not allowed")
    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<Error> handleException(OperationNotAllowedException e) {
        Error er = new Error(HttpStatus.FORBIDDEN, e.getLocalizedMessage());
        return new ResponseEntity<>(er, er.getHttpStatus());
    }*/
}
