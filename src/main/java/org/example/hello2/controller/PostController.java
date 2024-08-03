package org.example.hello2.controller;

import org.example.hello2.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/post-api")
public class PostController {

    @PostMapping("member1")
    public String postMember(@RequestBody Map<String, Object> postData) {

        StringBuilder sb = new StringBuilder();
        postData.forEach((key, value) ->
                sb
                        .append(key).append(" : ")
                        .append(value).append("\n"));
        return sb.toString();
    }

    @PostMapping("member2")
    public String postMember2(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }
}