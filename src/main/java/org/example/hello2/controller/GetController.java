package org.example.hello2.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping("param")
    public String param(
            @ApiParam(value = "이름", required = true)
            @RequestParam String name,

            @ApiParam(value = "이메일", required = true)
            @RequestParam String email,

            @ApiParam(value = "회사", required = true)
            @RequestParam String organization) {

        return name + "Hello World" + email + " / " + organization;
    }

    @GetMapping("memberDto")
    public String memberDto(MemberDto memberDto) {
        return memberDto.toString();
    }

}