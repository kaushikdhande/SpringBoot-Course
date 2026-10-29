package com.example.SpringbootCRUDdemo.service;

import com.example.SpringbootCRUDdemo.entity.Student;
import com.example.SpringbootCRUDdemo.repository.StudentRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    //1. endpoint listen(/app/students POST)

    //2. Business logic

    //3.interact with DB to store

    //4. response back to client(postman)

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student studReq){
         //business logic
        //store in db
        studReq.setDeleted(false);
        Student studentResp=studentRepository.save(studReq);
        return studentResp;
    }

    public Student getStudent(Long id) {
       Optional <Student> studentResp= studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentResp.isPresent()){
            return studentResp.get();
        }
       return null;
    }


    public List<Student> getAllStudent() {
        List<Student> studentList=studentRepository.findByDeletedIsFalse();
        return studentList;
    }

    public Student updateStudent(Long id,Student studentreq) {
        Optional <Student> existingStudent= studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave=existingStudent.get();
        studentToSave.setName(studentreq.getName());
        studentToSave.setAge(studentreq.getAge());
        studentToSave.setEmail(studentreq.getEmail());
        studentToSave.setRollNo(studentreq.getRollNo());
        studentToSave.setSubject(studentreq.getSubject());

        studentToSave.setDeleted(false);
       return studentRepository.save(studentToSave);


    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudent =studentRepository.existsById(id);

        if(!isStudent)return false;
        studentRepository.deleteById(id);

        return true;
    }

    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            return false;
        }
        Student studentToSave =existingStudent.get(); //get
        studentToSave.setDeleted(true);  //1
        studentRepository.save(studentToSave);
        return true;
    }
}
