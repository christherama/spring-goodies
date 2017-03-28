package io.rama.config;

import io.rama.web.request.LoggingHandlerInterceptorAdapter;
import io.rama.web.request.UserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


/**
 * Configuration of the MVC application
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new UserHandlerMethodArgumentResolver());
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggingHandlerInterceptorAdapter());
  }
}
