package io.rama.web;

import io.rama.user.UserNotFoundException;
import io.rama.web.error.Error;
import io.rama.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;


/**
 * Handles uncaught exceptions from HTTP requests
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

  /**
   * Handles a UserNotFoundException, producing a well-constructed error
   *
   * @param ex Uncaught exception
   * @return Error object, to be serialized by Jackson
   */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response userNotFound(UserNotFoundException ex) {
    return Response.builder().errors(
        Collections.singletonList(new Error(ex.getMessage()))
    ).build();
  }

  /**
   * Fallback handler for any exception not handled otherwise, producing a well-constructed error
   *
   * @param ex Uncaught exception
   * @return Error object, to be serialized by Jackson
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response unknownError(Exception ex) {
    log.error("{} occurred: {}", ex.getClass().getSimpleName(), ex.getMessage());
    return Response.builder().errors(
        Collections.singletonList(new Error("A server error occurred"))
    ).build();
  }
}
