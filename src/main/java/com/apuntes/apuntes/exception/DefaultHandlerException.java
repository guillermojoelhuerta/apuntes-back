package com.apuntes.apuntes.exception;

import dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DefaultHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HandleException.class)
    public ResponseEntity<ErrorResponse> handleException(HandleException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BindingResultException.class)
    public ResponseEntity<ErrorResponse> bindingResultException(BindingResultException ex) {
        //List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(e ->e.getDefaultMessage()).collect(Collectors.toList());
        String result = "";
        if (ex.getBindingResult().hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                errorMessages.append(error.getDefaultMessage()).append(".");
            }
            result = errorMessages.toString();
        }
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), result);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
