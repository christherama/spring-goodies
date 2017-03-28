package io.rama.web.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Encapsulates all data related to an HTTP request
 */
@Data
public class HttpRequest {
  private String path;
  private String method;
  private Map<String,String> headers;

  private HttpRequest(String path, String method, Map<String, String> headers) {
    this.path = path;
    this.method = method;
    this.headers = headers;
  }

  public static RequestBuilder builder() {
    return new RequestBuilder();
  }

  public static class RequestBuilder {
    private String path;
    private String method;
    private Map<String,String> headers;

    public RequestBuilder() {
      this.path = null;
      this.method = null;
      this.headers = new HashMap<>();
    }

    public RequestBuilder path(String path) {
      this.path = path;
      return this;
    }

    public RequestBuilder method(String method) {
      this.method = method.toLowerCase();
      return this;
    }

    public RequestBuilder header(String name, String value) {
      this.headers.put(name,value);
      return this;
    }

    public HttpRequest build() {
      return new HttpRequest(path,method,headers);
    }
  }
}
