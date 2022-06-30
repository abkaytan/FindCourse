package com.abkode.findcourse.data.repository;

import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.data.entity.CourseEntity;
import com.abkode.findcourse.data.entity.StudentEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
}
