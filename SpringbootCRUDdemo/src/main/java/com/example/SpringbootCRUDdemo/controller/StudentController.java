package com.example.SpringbootCRUDdemo.controller;

import com.example.SpringbootCRUDdemo.entity.Student;
import com.example.SpringbootCRUDdemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController  {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
//        System.out.println("inside Student Controller");
       Student createdStudent=studentService.createStudent(student);
//        System.out.println("outside Student Controller");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }


    // read student
    @GetMapping("/get")
    public ResponseEntity<Student> readStudent(@RequestParam Long id){
        Student studentResp=studentService.getStudent(id);
        if (studentResp ==null){
            return ResponseEntity.status(HttpStatus
                    .NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent(){
       List<Student> studentList= studentService.getAllStudent();
        if (studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }




    // update student
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,@RequestBody Student studentreq){
        Student studentResp=studentService.updateStudent(id,studentreq);
        if(studentResp ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentreq);
    }

    // delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
       Boolean isDeleted= studentService.deleteStudent(id);

       if (!isDeleted){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok("Record Deleted ");
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){
        Boolean isDeleted=studentService.deleteStudentSoftly(id);
        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted ");
    }
}
