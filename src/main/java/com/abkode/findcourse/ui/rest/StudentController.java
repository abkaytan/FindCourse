package com.abkode.findcourse.ui.rest;


import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.business.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;


    //ROOT
    //http://localhost:8080/api/v1/index
    @GetMapping({"/index","/"}  )
    public String getRoot(){
        return "index";
    }

    //LIST
    //http://localhos:8080/api/v1/students
    @GetMapping("/students")
    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentListDto = studentService.getAllStudents();
        return studentListDto;
    }


    //SAVE
    //http://localhost:8080/api/v1/students
    @PostMapping("/students")
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
        return studentDto;
    }


    //FIND
    //http://localhost:8080/api/v1/students/1
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        ResponseEntity<StudentDto> studentDto = studentService.getStudentById(id);
        return studentDto;
    }


    //UPDATE
    //http://localhost:8080/api/v1/students/1
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(studentDto);
    }


    //DELETE
    //http://localhost:8080/api/v1/students/1
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudentById(@PathVariable Long id) {

        studentService.deleteStudentById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
