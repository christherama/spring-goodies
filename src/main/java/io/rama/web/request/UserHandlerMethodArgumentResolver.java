package io.rama.web.request;

import io.rama.user.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * Resolves a controller method argument of type io.rama.user.User
 */
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(User.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    String userId = HttpUtils.getPathVariables(webRequest).get("id");
    String orgId = webRequest.getHeader("Org-Id");
    return User.builder().orgId(orgId).userId(userId).build();
  }
}
