package ch.skaldenmagic.cardmarket.autopricing.controller.error;

import ch.skaldenmagic.cardmarket.autopricing.domain.service.exceptions.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Central Exception Handler for API calls.
 *
 * @author Kevin Zellweger
 * @Date 01.11.20
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "Malformed JSON request";
    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      EntityNotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolation(
      ConstraintViolationException ex) {
    ApiError apiError = new ApiError(HttpStatus.CONFLICT);
    String constraintName = ex.getConstraintName();
    String cause = ex.getCause().getMessage();
    apiError.setMessage("Constraint Violation: " + constraintName + "\n" + cause);
    return buildResponseEntity(apiError);
  }


}
