package com.linux.oauth2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean("oAuth2RestTemplate")
  public OAuth2RestTemplate buildOAuth2RestTemplate(ClientCredentialsResourceDetails clientCredentialsResourceDetails) {
    return new OAuth2RestTemplate(clientCredentialsResourceDetails);
  }

  @Bean("restTemplate")
  public RestTemplate buildTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  @ConfigurationProperties(prefix = "security.oauth2.client")
  public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
    return new ClientCredentialsResourceDetails();
  }
}
