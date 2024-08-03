package org.example.hello2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {
    private String name;
    private String email;
    private String organization;

    @Override
    public String toString() {
        return
                "MemberDto [" +
                        "name=" + name + ", " +
                        "email=" + email + ", " +
                        "organization=" + organization + "]";
    }
}



