package com.zorvyn.xpensify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> notFound(NotFoundException pnf,WebRequest wr){
		return buildResponse(HttpStatus.NOT_FOUND,pnf.getMessage());
	}

    @ExceptionHandler(AuthException.class)
	public ResponseEntity<?> authException(AuthException pnf,WebRequest wr){
		return buildResponse(HttpStatus.FORBIDDEN,pnf.getMessage());
	}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException pnf,WebRequest wr){
        return buildResponse(HttpStatus.BAD_REQUEST,pnf.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        if (message == null) {
            errorResponse.put("message", "Something went wrong!");
        }
        errorResponse.put("status", status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

}
