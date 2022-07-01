package com.abkode.findcourse.data.repository;

import com.abkode.findcourse.data.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(e)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM StudentEntity e " +
            "WHERE e.email = ?1")
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT email FROM students WHERE requested_courses LIKE '%java%'")
    String[] getEmailsWhoSearchJava();
    /*
    @Query(nativeQuery = true, value = "select age AS age, count(age) AS count from employee e GROUP BY age")
    List<EmployeeAgeStatistics> getAgesWithGroupingWithNativeQuery();
     */
}
