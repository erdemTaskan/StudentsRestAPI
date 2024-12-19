package com.students.services.impl;

import com.students.dto.DtoCourse;
import com.students.dto.DtoStudents;
import com.students.dto.DtoStudentsIU;
import com.students.exception.BaseException;
import com.students.exception.ErrorMessage;
import com.students.exception.MessageType;
import com.students.model.Course;
import com.students.model.Students;
import com.students.repository.StudentRepository;
import com.students.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public DtoStudents saveStudents(DtoStudentsIU dtoStudentsIU) {
            DtoStudents response= new DtoStudents();
            Students student=new Students();

            BeanUtils.copyProperties(dtoStudentsIU,student);
            Students dbStudent= studentRepository.save(student);


            BeanUtils.copyProperties(dbStudent,response);
        return response;

    }

    @Override
    public List<DtoStudents> getAllStudents() {
        List<DtoStudents> dtolist= new ArrayList<>();
        List<Students> studentsList= studentRepository.findAll();
        for (Students students : studentsList){
            DtoStudents dtoStudents= new DtoStudents();
            BeanUtils.copyProperties(students,dtoStudents);
            if (students.getCourses()!=null && !students.getCourses().isEmpty()){
                for (Course course:students.getCourses()){
                    DtoCourse dtoCourse= new DtoCourse();
                    BeanUtils.copyProperties(course,dtoCourse);
                    dtoStudents.getCourses().add(dtoCourse);
                }
            }
            dtolist.add(dtoStudents);
        }

        return dtolist;
    }

    @Override
    public DtoStudents getStudentById(Integer id) {
        DtoStudents dtoStudents=new DtoStudents();

       Optional<Students> optional= studentRepository.findById(id);

       if (optional.isEmpty()){
           throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
       }
       Students dbStudents= optional.get();
       BeanUtils.copyProperties(dbStudents,dtoStudents);
     if (dbStudents.getCourses()!=null && !dbStudents.getCourses().isEmpty()){
         for (Course course : dbStudents.getCourses()){
             DtoCourse dtoCourse=new DtoCourse();
             BeanUtils.copyProperties(course,dtoCourse);

             dtoStudents.getCourses().add(dtoCourse);
         }
     }

        return dtoStudents;
    }

    @Override
    public void deleteStudent(Integer id) {
       Optional<Students>optional= studentRepository.findById(id);
        if (optional.isPresent()){
            studentRepository.delete(optional.get());
        }


    }

    @Override
    public DtoStudents updateStudent(Integer id, DtoStudentsIU dtoStudentsIU) {
        DtoStudents dtoStudents = new DtoStudents();

       Optional<Students> optional= studentRepository.findById(id);
       if (optional.isPresent()){
           Students dbStudents= optional.get();

           dbStudents.setFirstname(dtoStudentsIU.getFirstname());
           dbStudents.setLastname(dtoStudentsIU.getLastname());
           dbStudents.setBirtOfdate(dtoStudentsIU.getBirtOfdate());

          Students updatedStudents= studentRepository.save(dbStudents);
            BeanUtils.copyProperties(updatedStudents,dtoStudents);
            return dtoStudents;
       }
       return null;
    }



}
