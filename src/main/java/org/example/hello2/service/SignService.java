package org.example.hello2.service;

import org.example.hello2.data.dto.SignInResultDto;
import org.example.hello2.data.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    SignInResultDto signIn(String id , String password) throws RuntimeException;

}
