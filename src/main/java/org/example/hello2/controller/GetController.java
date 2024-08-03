package org.example.hello2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/get-api")
public class GetController {
    public String hello() {
        return "Hello World";
    }
}