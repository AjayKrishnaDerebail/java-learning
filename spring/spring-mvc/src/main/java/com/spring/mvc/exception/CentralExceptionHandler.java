package com.spring.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralExceptionHandler {

  /**
   * To handle null pointer exception To handle any exception you can use Exception class
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({NullPointerException.class})
  public String exceptionHandlerForNPE(Model model) {
    model.addAttribute("nullException", "Null pointer exception occurred");
    return "nullPage";
  }
}
