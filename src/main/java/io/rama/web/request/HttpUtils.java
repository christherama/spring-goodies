package io.rama.web.request;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;


/**
 * Utility class for HTTP requests
 */
public class HttpUtils {
  /**
   * Gets the path variables of a web request in key/value pairs
   *
   * @param webRequest Web request containing path variables
   * @return Key/value pairs of path variables from web request
   */
  @SuppressWarnings("unchecked")
  public static Map<String, String> getPathVariables(NativeWebRequest webRequest) {
    Object pathVariables = webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
    return (Map<String, String>) pathVariables;
  }
}
