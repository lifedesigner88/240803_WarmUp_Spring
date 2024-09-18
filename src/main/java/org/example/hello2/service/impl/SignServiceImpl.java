package org.example.hello2.service.impl;

import org.example.hello2.common.CommonResponse;
import org.example.hello2.config.security.JwtTokenProvider;
import org.example.hello2.data.dto.SignInResultDto;
import org.example.hello2.data.dto.SignUpResultDto;
import org.example.hello2.data.entity.User;
import org.example.hello2.data.repository.UserRepository;
import org.example.hello2.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {


    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SignUpResultDto signUp(String id, String password, String name, String role) {

        User user;
        if (role.equalsIgnoreCase("admin"))
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        else
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignInResultDto();

        if (!savedUser.getName().isEmpty())
            setSuccessResult(signUpResultDto);
        else
            setFailResult(signUpResultDto);

        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {

        User user = userRepository.getByUid(id);

        if (user != null && !passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException();

        assert user != null;
        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(
                        jwtTokenProvider
                                .createToken(
                                        String.valueOf(user.getUid()),
                                        user.getRoles()
                                )
                )
                .build();


        setSuccessResult(signInResultDto);
        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}
