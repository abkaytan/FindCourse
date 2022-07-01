package com.abkode.findcourse.data.repository;

import com.abkode.findcourse.business.dto.StudentDto;
import com.abkode.findcourse.data.entity.CourseEntity;
import com.abkode.findcourse.data.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT email FROM students WHERE requested_courses LIKE '%java%'")
    String[] getEmailsWhoSearchJava();
    /*
    @Query(nativeQuery = true, value = "select age AS age, count(age) AS count from employee e GROUP BY age")
    List<EmployeeAgeStatistics> getAgesWithGroupingWithNativeQuery();
     */

}
