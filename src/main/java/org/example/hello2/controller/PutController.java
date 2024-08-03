package org.example.hello2.controller;

import org.example.hello2.dto.MemberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/put-api")
public class PutController {

    @PutMapping("member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuffer sb = new StringBuffer();
        putData.forEach((key, value) -> sb
                .append(key).append(" : ")
                .append(value).append("\n")
        );
        return sb.toString();
    }

    @PutMapping("member1")
    public MemberDto putMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto;
    }
}