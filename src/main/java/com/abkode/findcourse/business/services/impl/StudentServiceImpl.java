package com.abkode.findcourse.business.services.impl;

import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.business.services.StudentService;
import com.abkode.findcourse.data.entity.StudentEntity;
import com.abkode.findcourse.data.repository.StudentRepository;
import com.abkode.findcourse.exception.ResourceNotFoundException;
import com.abkode.findcourse.exception.SameEmailException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    //LIST
    //http://localhos:8080/api/v1/students
    @GetMapping("/students")
    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentListDto = new ArrayList<>();
        Iterable<StudentEntity> students = studentRepository.findAll();
        for(StudentEntity student: students){
            StudentDto studentDto = studentEntityToDto(student);
            studentListDto.add(studentDto);
        }
        return studentListDto;
    }


    //SAVE
    //http://localhos:8080/api/v1/students
    @PostMapping("/students")
    @Override
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        StudentEntity studentEntity = studentDtoToEntity(studentDto);
        boolean isExist = studentRepository.existsByEmail(studentEntity.getEmail());
        if (isExist) {
            throw new SameEmailException("Student already signed up with same email: " + studentEntity.getEmail());
        }
        studentRepository.save(studentEntity);
        return studentDto;
    }

    /*
    Annotations and and requests added to write codes more properly
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentDtoToEntity(studentDto);
        studentRepository.save(studentEntity);
        return studentDto;
    }
    */


    //FIND
    //http://localhost:8080/api/v1/students/1
    @GetMapping("/students/{id}")
    @Override
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentEntity student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("böyle bir ID ' e kayıtlı kimse yok : " + id));
        StudentDto studentDto = studentEntityToDto(student);
        return ResponseEntity.ok(studentDto);
    }


    //UPDATE
    //http://localhost:8080/api/v1/students/1
    @PutMapping("/students/{id}")
    @Override
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,@RequestBody StudentDto studentDto) {

        StudentEntity studentEntity = studentDtoToEntity(studentDto);

        boolean isExist = studentRepository.existsByEmail(studentEntity.getEmail());
        if (isExist) {
            throw new SameEmailException("Student already signed up with same email: " + studentEntity.getEmail());
        }

        StudentEntity student =studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("böyle bir ID ' e kayıtlı kimse yok : " + id));

        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setEmail(studentEntity.getEmail());
        student.setRequestedCourses(studentEntity.getRequestedCourses());

        StudentEntity updatedStudent = studentRepository.save(student);
        StudentDto studentDtoUpdated = studentEntityToDto(updatedStudent);

        return ResponseEntity.ok(studentDtoUpdated);
    }


    //DELETE
    //http://localhost:8080/api/v1/courses/1
    @DeleteMapping("/students/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteStudentById(@PathVariable Long id) {

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bu ID ile kayıtlı öğrenci yok : " + id));

        studentRepository.delete(student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }



    //////======> MAPPERS ----------------

    // model mappers -- DTO ==> ENTITY
    @Override
    public StudentEntity studentDtoToEntity(StudentDto studentDto) {
        StudentEntity studentEntity = modelMapper.map(studentDto, StudentEntity.class);
        return studentEntity;
    }

    // model mappers -- ENTITY ==> DTO
    @Override
    public StudentDto studentEntityToDto(StudentEntity studentEntity) {
        StudentDto studentDto = modelMapper.map(studentEntity, StudentDto.class);
        return studentDto;
    }
}
