package com.learn.restful.controllers;

import com.learn.restful.models.HelloBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping("/hello/{name}")
    public HelloBean helloBean(@PathVariable String name) {
        return new HelloBean(String.format("Hello %s", name));
    }
}
