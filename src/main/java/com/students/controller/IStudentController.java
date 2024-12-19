package com.students.controller;

import com.students.dto.DtoStudents;
import com.students.dto.DtoStudentsIU;
import com.students.model.Students;

import java.util.List;

public interface IStudentController {

    public DtoStudents saveStudent(DtoStudentsIU dtoStudentsIU);

    public List<DtoStudents> getAllStudents();

    public DtoStudents getStudentById(Integer id);

    public void  deleteStudent(Integer id);

    public DtoStudents updateStudent(Integer id, DtoStudentsIU dtoStudentsIU);
}
