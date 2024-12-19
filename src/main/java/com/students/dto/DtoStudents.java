package com.students.dto;

import com.students.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudents {
    private String firstname;

    private String lastname;

    private List<DtoCourse> courses = new ArrayList<>();

}
