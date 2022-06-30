package com.abkode.findcourse.business.services;

import com.abkode.findcourse.business.dto.CourseDto;
import com.abkode.findcourse.data.entity.CourseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CourseService {

    //CRUD
    public List<CourseDto> getAllCourse();
    public String[] getStudentsWhoWantsJava();
    public CourseDto createCourse(CourseDto courseDto);
    public ResponseEntity<CourseDto> getCourseById(Long id);
    public ResponseEntity<CourseDto> updateCourse(Long id, CourseDto courseDto);
    public ResponseEntity<Map<String, Boolean>> deleteCourseById(Long id);


    //MODEL MAPPERS
    public CourseEntity courseDtoToEntity(CourseDto courseDto);
    public CourseDto courseEntityToDto(CourseEntity courseEntity);

}
