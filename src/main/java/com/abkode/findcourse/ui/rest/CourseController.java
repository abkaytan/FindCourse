package com.abkode.findcourse.ui.rest;
import com.abkode.findcourse.business.dto.CourseDto;
import com.abkode.findcourse.business.services.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CourseController {


    @Autowired
    private CourseServiceImpl courseServiceImpl;

    //LIST
    //http://localhost:8080/api/v1/courses
    @GetMapping("/courses")
    public List<CourseDto> getAllCourse() {
        return courseServiceImpl.getAllCourse();
    }

    //LIST
    //http://localhost:8080/api/v1/courses/java
    @GetMapping("/courses/java")
    public String[] getStudentsWhoWantsJava() {
        return courseServiceImpl.getStudentsWhoWantsJava();
    }

    //SAVE
    //http://localhost:8080/api/v1/courses
    @PostMapping("/courses")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return courseServiceImpl.createCourse(courseDto);
    }

    //FIND
    //http://localhost:8080/api/v1/courses/1
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return courseServiceImpl.getCourseById(id);
    }

    //UPDATE
    //http://localhost:8080/api/v1/courses/1
    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id,@RequestBody  CourseDto courseDto) {
        return courseServiceImpl.updateCourse(id, courseDto);
    }

    //DELETE
    //http://localhost:8080/api/v1/courses/1
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourseById(@PathVariable Long id) {
        return courseServiceImpl.deleteCourseById(id);
    }
}
