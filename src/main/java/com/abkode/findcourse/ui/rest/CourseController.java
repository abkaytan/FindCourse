package com.abkode.findcourse.ui.rest;

import com.abkode.findcourse.business.dto.CourseDto;
import com.abkode.findcourse.business.services.CourseService;
import com.abkode.findcourse.business.services.impl.CourseServiceImpl;
import com.abkode.findcourse.data.entity.CourseEntity;
import com.abkode.findcourse.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //LIST
    //http://localhos:8080/api/v1/courses
    @GetMapping("/courses")
    public List<CourseDto> getAllCourse() {
        List<CourseDto> courseDtoList = courseService.getAllCourse();
        return courseDtoList;
    }

    //SAVE
    //http://localhost:8080/api/v1/courses
    @PostMapping("/courses")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        courseService.createCourse(courseDto);
        return courseDto;
    }


    //FIND
    //http://localhost:8080/api/v1/courses/1
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        ResponseEntity<CourseDto> courseDto = courseService.getCourseById(id);
        return courseDto;
    }


    //UPDATE
    //http://localhost:8080/api/v1/courses/1
    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,@RequestBody  CourseDto courseDto) {
        return courseService.updateCourse(id, courseDto);
    }


    //DELETE
    //http://localhost:8080/api/v1/courses/1
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourseById(@PathVariable Long id) {

        return courseService.deleteCourseById(id);

        /*Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);*/

    }
}
