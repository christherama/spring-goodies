package io.rama.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


/**
 * Handles logging that should occur before and/or after each HTTP request
 */
@Slf4j
public class LoggingHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Enumeration<String> headerNames = request.getHeaderNames();

    HttpRequest.RequestBuilder requestBuilder = HttpRequest.builder()
        .path(request.getRequestURI())
        .method(request.getMethod());

    while(headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      String value = request.getHeader(key);
      requestBuilder.header(key,value);
    }

    HttpRequest httpRequest = requestBuilder.build();
    ObjectMapper mapper = new ObjectMapper();
    log.info("HttpRequest: " + mapper.writeValueAsString(httpRequest));
    return true;
  }
}
