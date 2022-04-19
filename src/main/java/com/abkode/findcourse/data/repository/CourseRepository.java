package com.abkode.findcourse.data.repository;

import com.abkode.findcourse.data.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
}
