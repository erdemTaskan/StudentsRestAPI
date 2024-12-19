package com.students.controller.impl;

import com.students.controller.IStudentController;
import com.students.dto.DtoStudents;
import com.students.dto.DtoStudentsIU;
import com.students.model.Students;
import com.students.services.IStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/students")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;



    @PostMapping(path = "/save")
    @Override
    public DtoStudents saveStudent(@RequestBody @Valid DtoStudentsIU dtoStudentsIU) {
        return studentService.saveStudents(dtoStudentsIU);
    }



    @GetMapping(path = "/list")
    @Override
    public List<DtoStudents> getAllStudents() {
        return studentService.getAllStudents();
    }



    @GetMapping(path = "/list/{id}")
    @Override
    public DtoStudents getStudentById(@PathVariable(name = "id") Integer id) {
        return studentService.getStudentById(id);
    }



    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteStudent(@PathVariable(name = "id") Integer id) {

        studentService.deleteStudent(id);
    }



    @PutMapping(path = "/update/{id}")
    @Override
    public DtoStudents updateStudent(@PathVariable(name = "id") Integer id,@RequestBody DtoStudentsIU dtoStudentsIU) {
        return studentService.updateStudent(id,dtoStudentsIU);
    }
}
