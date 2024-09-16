package org.example.hello2.data.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp="^010-\\d{4}-\\d{4}$")
    String phoneNumber;

    @Min(value = 20) @Max(value = 40)
    int age;

    @Size(min = 0, max = 40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;

}
