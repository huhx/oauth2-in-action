package com.linux.oauth2.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final RequestMappingHandlerMapping requestMappingHandlerMapping;

  @Value("${security.oauth2.resource.id}")
  private String resourceId;

  @Value("${security.oauth2.resource.jwk.keySetUri}")
  private String keySetUri;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
            .requestMatchers()
            .mvcMatchers(securedPatterns())
            .and()
            .authorizeRequests()
            .anyRequest()
            .fullyAuthenticated();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(resourceId).tokenStore(new JwkTokenStore(keySetUri));
  }

  private String[] securedPatterns() {
    return requestMappingHandlerMapping.getHandlerMethods().entrySet().stream()
            .filter(entry -> entry.getValue().getMethodAnnotation(PreAuthorize.class) != null)
            .flatMap(entry -> entry.getKey().getPatternsCondition().getPatterns().stream())
            .toArray(String[]::new);
  }

}
