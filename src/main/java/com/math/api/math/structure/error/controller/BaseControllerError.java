package com.math.api.math.structure.error.controller;

import com.math.api.math.structure.error.ProcessException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(0)
public class BaseControllerError {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ErrorMessage> exception(final ProcessException ex) {
    return ResponseEntity.badRequest().body(
        ErrorMessage.builder()
            .message(ex.getMessage())
            .build());
  }

}
