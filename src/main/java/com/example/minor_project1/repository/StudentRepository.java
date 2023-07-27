package com.example.minor_project1.repository;

import com.example.minor_project1.models.Student;
import com.fasterxml.classmate.AnnotationOverrides;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Integer> {

}
