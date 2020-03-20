package com.linux.oauth2.controller;

import com.linux.oauth2.domain.HelloResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ClientController {

  private final OAuth2RestTemplate oAuth2RestTemplate;

  private final RestTemplate restTemplate;

  @GetMapping("/token")
  public ResponseEntity<HelloResult> sayHelloWithToken() {
    return oAuth2RestTemplate.getForEntity("http://localhost:9090/server", HelloResult.class);
  }

  @GetMapping("/none")
  public ResponseEntity<HelloResult> sayHelloWithoutToken() {
    return restTemplate.getForEntity("http://localhost:9090/server/none", HelloResult.class);
  }
}
