package io.rama.web.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates an error contained in an HTTP response body,
 * containing only a message (for now)
 */
@Data
@Builder
@AllArgsConstructor
public class Error {
  private String message;
}
