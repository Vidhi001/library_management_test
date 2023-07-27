package com.example.minor_project1.services;

import com.example.minor_project1.dtos.CreateStudentRequest;
import com.example.minor_project1.models.SecuredUser;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.repository.StudentCacheRepository;
import com.example.minor_project1.repository.StudentRepository;
import com.example.minor_project1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentServices {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;

    @Autowired
    StudentCacheRepository studentCacheRepository;


    public void create(Student studentRequest) {
        SecuredUser securedUser = studentRequest.getSecuredUser();
        userService.save(securedUser, Constants.STUDENT_USER);
//        studentRequest.setSecuredUser(securedUser);
        studentRepository.save(studentRequest);
    }

    public Student getOtherDetails(Integer id){
        Student student  = studentRepository.findById(id).orElse(null);
        return student;
    }
    public Student get(Integer id) {
        Student student = studentCacheRepository.get(id);
        if (student != null) {
            return student;
        }

        student  = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentCacheRepository.set(student);
        }
        return student;
    }
}
