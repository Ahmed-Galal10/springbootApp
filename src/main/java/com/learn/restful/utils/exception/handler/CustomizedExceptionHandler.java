package com.learn.restful.utils.exception.handler;

import com.learn.restful.utils.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex) {
        ExceptionDetails exceptionDetails = this.getExceptionDetails(ex, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionDetails getExceptionDetails(Exception ex, HttpStatus status) {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(new Date());
        exceptionDetails.setStatus(String.valueOf(status.value()));
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setDetails("error details");

        return exceptionDetails;
    }

}
