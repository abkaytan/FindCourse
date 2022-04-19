package com.abkode.findcourse.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class StudentDto {

    private String firstName;
    private String lastName;
    private String email;
    private String requestedCourses;

}
