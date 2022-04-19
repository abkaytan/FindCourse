package com.abkode.findcourse.business.services;


import com.abkode.findcourse.business.dto.CourseDto;
import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.data.entity.CourseEntity;
import com.abkode.findcourse.data.entity.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {

    //CRUD
    public List<StudentDto> getAllStudents();
    public StudentDto createStudent(StudentDto studentDto);
    public ResponseEntity<StudentDto> getStudentById(Long id);
    public ResponseEntity<StudentDto> updateStudent(Long id, StudentDto studentDto);
    public ResponseEntity<Map<String, Boolean>> deleteStudentById(Long id);

    //MODEL MAPPERS
    public StudentEntity studentDtoToEntity(StudentDto studentDto);
    public StudentDto studentEntityToDto(StudentEntity studentEntity);




}
