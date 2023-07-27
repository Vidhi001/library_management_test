package com.example.minor_project1.dtos;

import com.example.minor_project1.models.Admin;
import lombok.*;
import com.example.minor_project1.models.SecuredUser;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Admin to(){
        return Admin.builder()
                .Name(this.name)
                .email(this.email)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }

}
