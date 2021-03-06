package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestErrorController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ObjectError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getAllErrors();
    }
}
