package com.example.minor_project1.repository;

import com.example.minor_project1.config.CacheConfig;
import com.example.minor_project1.models.Student;
import com.example.minor_project1.services.TransactionServices;
import com.example.minor_project1.utils.Constants;
import com.example.minor_project1.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentCacheRepository {

    Logger logger = LoggerFactory.getLogger(StudentCacheRepository.class);
    @Autowired
    CacheConfig cacheConfig;

    public Student get(Integer studentId){
        Object studentData = cacheConfig.getTemplate().opsForValue().get(getKey(studentId));
        logger.debug("student data {}",studentData);
        Student data = (Student) studentData;
        return studentData==null ? null : data;

    }

    public void set(Student student){
//        Student updatedStudent = Utils.removeBookAndTransaction(student);
        cacheConfig.getTemplate().opsForValue().set(getKey(student.getId()),student);

    }

    private String getKey(Integer studentId){
        return Constants.STUDENT_CACHE_KEY_PREFIX + studentId;
    }
}
