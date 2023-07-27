package com.example.minor_project1.dtos;


import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.repository.StudentRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateStudentRequest {

    @NotBlank
    private String studentName;

    @NotBlank
    private String studentEmail;

    private Integer age;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to(){
        return Student.builder()
                .studentName(this.studentName)
                .studentEmail(this.studentEmail)
                .age(this.age)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}


// NotNull  - Not null & can be empty
// NotEmpty - Not null + size>0  Ex - " "
// NotBlank - not null + strip size>0  Ex = " "
