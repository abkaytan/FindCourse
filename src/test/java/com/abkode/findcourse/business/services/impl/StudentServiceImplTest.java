package com.abkode.findcourse.business.services.impl;

import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.data.entity.StudentEntity;
import com.abkode.findcourse.data.repository.StudentRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.abkode.findcourse.exception.ResourceNotFoundException;
import com.abkode.findcourse.exception.SameEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @BeforeEach
    public void tearDown(){
        studentRepositoryTest.deleteAll();
    }

    @Mock
    StudentRepository studentRepositoryTest;

    @Mock
    ModelMapper modelMapperTest;

    @InjectMocks
    StudentServiceImpl studentServiceImplTest;


    @Test
    void getAllStudents() {
        //given
        StudentEntity student = new StudentEntity("ali", "bkaytan", "a.gmail.com", "java, react");
        when(studentRepositoryTest.findAll()).thenReturn(Collections.singletonList(student));
        StudentDto studentDto = new StudentDto("ali", "bkaytan", "a.gmail.com", "java, react");
        when(studentServiceImplTest.studentEntityToDto(student)).thenReturn(studentDto);
        /*StudentDto studentDto = modelMapperTest.map(student, StudentDto.class);
        Mockito.when(studentEntityToDto(student)).thenReturn(studentDto);*/
        //when
        List<StudentDto> actual = studentServiceImplTest.getAllStudents();
        //then
        assertEquals("a.gmail.com" , actual.get(0).getEmail());
    }

    @Test
    void createStudent1() {
        //given
        StudentDto studentDto = new StudentDto("ali", "bkaytan", "a.gmail.com", "java, react");
        StudentEntity studentEntity = new StudentEntity("ali", "bkaytan", "a.gmail.com", "java, react");
        when(studentRepositoryTest.existsByEmail(any())).thenReturn(false);
        when(studentRepositoryTest.save(any())).thenReturn(studentEntity);
        when(studentServiceImplTest.studentDtoToEntity(studentDto)).thenReturn(studentEntity);
        //when
        StudentDto actual = this.studentServiceImplTest.createStudent(studentDto);
        //then
        assertEquals(studentEntity.getEmail(), actual.getEmail());

    }
    @Test
    void createStudent2() {
        //given
        StudentDto studentDto = new StudentDto("ali", "bkaytan", "a.gmail.com", "java, react");
        StudentEntity studentEntity = new StudentEntity("ali", "bkaytan", "a.gmail.com", "java, react");
        when(studentServiceImplTest.studentDtoToEntity(studentDto)).thenReturn(studentEntity);
        when(studentRepositoryTest.existsByEmail(anyString())).thenReturn(Boolean.TRUE);
        //when
        Executable executable = () -> studentServiceImplTest.createStudent(studentDto);
        //then
        assertThrows(SameEmailException.class, executable);

    }

    /*@Test
    void getStudentById() {
        //given
        StudentEntity student = new StudentEntity("ali", "bkaytan",
                "a.gmail.com", "java, react");
        //StudentEntity student = this.studentServiceImplTest.studentDtoToEntity(studentDto);
        long id =1 ;
        student.setId(id);
        *//*StudentDto studentDto = new StudentDto("ali", "bkaytan",
                "a.gmail.com", "java, react");*//*
        when(studentRepositoryTest.findById(id).orElseThrow()).thenReturn(student);
        when(studentServiceImplTest.studentEntityToDto(student)).thenReturn();
        //when
        ResponseEntity<StudentDto> actual = this.studentServiceImplTest.getStudentById(id);
        //then
        assertEquals(student.getFirstName(), actual.getBody().getFirstName());

    }*/

    @Test
    void getStudentById2() {
        //given
        //when
        Executable executable = () -> this.studentServiceImplTest.getStudentById(anyLong());
        //then
        assertThrows(ResourceNotFoundException.class, executable);

    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudentById() {
    }

    public StudentEntity studentDtoToEntity(StudentDto studentDto) {
        StudentEntity studentEntity = modelMapperTest.map(studentDto, StudentEntity.class);
        return studentEntity;
    }

    public StudentDto studentEntityToDto(StudentEntity studentEntity) {
        StudentDto studentDto = modelMapperTest.map(studentEntity, StudentDto.class);
        return studentDto;
    }
}