package com.abkode.findcourse.ui.rest;

import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.business.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    //ROOT
    //http://localhost:8080/api/v1/index
    @GetMapping({"/index", "/"})
    public String getRoot() {
        return "index";
    }

    //LIST
    //http://localhost:8080/api/v1/students
    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    //SAVE
    //http://localhost:8080/api/v1/students
    @PostMapping("/students")
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        return studentServiceImpl.createStudent(studentDto);
    }

    //FIND
    //http://localhost:8080/api/v1/students/1
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return studentServiceImpl.getStudentById(id);

    }

    //UPDATE
    //http://localhost:8080/api/v1/students/1
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentServiceImpl.updateStudent(id, studentDto);
    }

    //DELETE
    //http://localhost:8080/api/v1/students/1
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudentById(@PathVariable Long id) {
        return studentServiceImpl.deleteStudentById(id);
    }

}
