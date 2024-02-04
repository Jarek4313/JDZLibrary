package com.jdz;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String login;
    private String password;
    private Role role;//??
}
