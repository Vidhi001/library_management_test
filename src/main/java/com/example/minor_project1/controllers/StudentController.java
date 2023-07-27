package com.example.minor_project1.controllers;


import com.example.minor_project1.dtos.CreateStudentRequest;
import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.services.StudentServices;
import com.example.minor_project1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Arrays.stream;

@RestController
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentServices studentServices;

    @PostMapping("/student")
    public void createStudent(@RequestBody @Valid CreateStudentRequest studentRequest){
        studentServices.create(studentRequest.to());

    }

    // For Admin : To see details of any student by Id
    @GetMapping("/student-by-id")
    public Student getStudentById(@RequestParam("Id") int id) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser loginUser = (SecuredUser) authentication.getPrincipal();
        boolean isAdmin = loginUser.getAuthorities().stream().anyMatch(x -> Constants.STUDENT_BY_ID_DETAILS.equals(x.getAuthority()));
        if (isAdmin == true) {
            return studentServices.get(id);
        }
        throw new Exception("User is not authorized to do this");


    }

    // For Student : To see own details only
    @GetMapping("/student")
    public Student getStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser loginUser = (SecuredUser) authentication.getPrincipal();
        Integer studentId = loginUser.getStudent().getId();
        return studentServices.get(studentId);
    }

}
