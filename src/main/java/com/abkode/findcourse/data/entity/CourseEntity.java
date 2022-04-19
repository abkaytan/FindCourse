package com.abkode.findcourse.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@Log4j2

@Entity
@Table(name="course")
public class CourseEntity extends BaseEntity implements Serializable {

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_address")
    private String courseAddress;

    @Column(name = "email")
    private String courseEmail;

    @Column(name = "course_types")
    private String courseTypes;

}
