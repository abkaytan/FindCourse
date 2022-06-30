package com.abkode.findcourse.business.services.impl;

import com.abkode.findcourse.business.dto.CourseDto;
import com.abkode.findcourse.business.services.CourseService;
import com.abkode.findcourse.data.entity.CourseEntity;
import com.abkode.findcourse.data.repository.CourseRepository;
import com.abkode.findcourse.data.repository.StudentRepository;
import com.abkode.findcourse.exception.ResourceNotFoundException;
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
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;


    //LIST
    //http://localhos:8080/api/v1/courses
    @GetMapping("/courses")
    @Override
    public List<CourseDto> getAllCourse() {
        List<CourseDto> courseDtoList = new ArrayList<>();
        Iterable<CourseEntity> courses = courseRepository.findAll();
        for(CourseEntity courseEntity : courses) {
            CourseDto courseDto = courseEntityToDto(courseEntity);
            courseDtoList.add(courseDto);

        }
        return courseDtoList;
    }

    //LIST
    //http://localhos:8080/api/v1/courses/java
    @GetMapping("/courses/java")
    @Override
    public String[] getStudentsWhoWantsJava() {
        String emails[] = studentRepository.getEmailsWhoSearchJava();
        return emails;
    }

    //SAVE
    //http://localhost:8080/api/v1/courses
    @PostMapping("/courses")
    @Override
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        CourseEntity courseEntity = courseDtoToEntity(courseDto);
        courseRepository.save(courseEntity);
        return courseDto;
    }


    //FIND
    //http://localhost:8080/api/v1/courses/1
    @GetMapping("/courses/{id}")
    @Override
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bu ID ye kayıtlı kurs firması bulunamadı : " + id));
        CourseDto courseDto = courseEntityToDto(courseEntity);
        return ResponseEntity.ok(courseDto);
    }


    //UPDATE
    //http://localhost:8080/api/v1/courses/1
    @PutMapping("/courses/{id}")
    @Override
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,@RequestBody  CourseDto courseDto) {

        CourseEntity courseEntity = courseDtoToEntity(courseDto);

        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bu ID' ye kayıtlı kurs bulunmamaktadır: " + id));

        course.setCourseName(courseEntity.getCourseName());
        course.setCourseEmail(courseEntity.getCourseEmail());
        course.setCourseAddress(courseEntity.getCourseAddress());
        course.setCourseTypes(courseEntity.getCourseTypes());

        CourseEntity updatedCourse = courseRepository.save(course);

        CourseDto updatedCourseDto = courseEntityToDto(updatedCourse);

        return ResponseEntity.ok(updatedCourseDto);
    }


    //DELETE
    //http://localhost:8080/api/v1/courses/1
    @DeleteMapping("/courses/{id}")
    @Override
    public ResponseEntity<Map<String, Boolean>> deleteCourseById(@PathVariable Long id) {

        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Bu ID ile kayıtlı kurs yok : " + id));

        courseRepository.delete(course);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }



    //////======> MAPPERS ----------------

    // model mappers -- DTO ==> ENTITY
    @Override
    public CourseEntity courseDtoToEntity(CourseDto courseDto) {
        CourseEntity courseEntity = modelMapper.map(courseDto, CourseEntity.class);
        return courseEntity;
    }

    // model mappers -- ENTITY ==> DTO
    @Override
    public CourseDto courseEntityToDto(CourseEntity courseEntity) {
        CourseDto courseDto = modelMapper.map(courseEntity, CourseDto.class);
        return courseDto;
    }
}
