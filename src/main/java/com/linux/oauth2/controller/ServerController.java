package com.linux.oauth2.controller;

import com.linux.oauth2.domain.HelloResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

  @GetMapping()
  @PreAuthorize("true")
  public HelloResult sayHello() {
    return buildHelloResult();
  }

  @GetMapping("/none")
  public HelloResult sayHelloWorld() {
    return buildHelloResult();
  }

  private HelloResult buildHelloResult() {
    return new HelloResult("Hello World, 2020.", "You mean everything to me.", "2020-11-11 11:11:11");
  }
}
