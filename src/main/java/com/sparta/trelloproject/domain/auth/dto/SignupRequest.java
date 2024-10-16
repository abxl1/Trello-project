package com.sparta.trelloproject.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
//    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
//    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
//            message = "비밀번호는 대소문자 포함 영문 + 숫자 + 특수문자 최소 1글자 포함, 최소 8글자 이상")
    private String password;

    @NotBlank(message = "권한은 공백일 수 없습니다.")
    private String userRole;
}