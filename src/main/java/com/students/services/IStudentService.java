package com.students.services;

import com.students.dto.DtoStudents;
import com.students.dto.DtoStudentsIU;
import com.students.model.Students;

import java.util.List;

public interface IStudentService {

    public DtoStudents saveStudents(DtoStudentsIU students);

    public List<DtoStudents> getAllStudents();

    public DtoStudents getStudentById(Integer id);

    public void deleteStudent(Integer id);

    public DtoStudents updateStudent(Integer id, DtoStudentsIU dtoStudentsIU);
}
