package org.example.hello2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/get-api")
public class GetController {

    @GetMapping("name")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("variable1/{variable}")
    public String variable1(@PathVariable String variable) {
        return variable;
    }
}