package io.rama.web.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.rama.web.error.Error;
import lombok.Builder;

import java.util.List;


/**
 * Model for every HTTP response
 */
@lombok.Data
@Builder
public class Response {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Error> errors;
}
