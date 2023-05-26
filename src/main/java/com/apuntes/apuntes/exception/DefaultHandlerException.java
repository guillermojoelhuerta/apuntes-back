package com.apuntes.apuntes.exception;

import dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DefaultHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> nameException(NameException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HandleException.class)        //Exception ex
    public ResponseEntity<ErrorResponse> handleException(HandleException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<ExceptionDetails> demoException(Exception ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails("error !!!", ex.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //throw new FileNotFoundException("El archivo no existe");
    //throw new NameException("el nombre del archivo esta mal!!!!");
    //throw new HandleException("Algo está mal");
    //throw new RequestException("500","El archivo no existe.");
    //throw new BusinessException("P-300", HttpStatus.INTERNAL_SERVER_ERROR, "Email already exist.");
    // throw new DemoException("aqui hay un error XXXXXXX",
    // new ExceptionDetails("Error inesperado XXXXXXX", "Error"));
    //throw new DemoException("aqui hay un error XXXXXXX", new ExceptionDetails("Error inesperado XXXXXXX", "Error"));
}
