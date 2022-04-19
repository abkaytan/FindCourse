package com.abkode.findcourse.data.repository;

import com.abkode.findcourse.data.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
}
