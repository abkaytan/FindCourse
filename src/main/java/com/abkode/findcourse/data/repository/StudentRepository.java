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
}
