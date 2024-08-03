package org.example.hello2.controller;

import org.example.hello2.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("param")
    public String param(@RequestParam String name,
                        @RequestParam String email,
                        @RequestParam String organization) {

        return name + "Hello World" + email + "/" + organization;
    }

    @GetMapping("memberDto")
    public String memberDto(MemberDto memberDto) {
        return memberDto.toString();
    }

}