package com.example.dto_demo.controller;

import com.example.dto_demo.dto.CreateStudentRequestDTO;
import com.example.dto_demo.dto.CreateStudentResponseDTO;
import com.example.dto_demo.dto.UpdateStudentRequestDTO;
import com.example.dto_demo.dto.UpdateStudentResponseDTO;
import com.example.dto_demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
     private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //create
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            @RequestBody CreateStudentRequestDTO createStudentRequestDTO){
    CreateStudentResponseDTO createdStudent =studentService.createStudent(createStudentRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    //get
    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@RequestParam Long id){
        CreateStudentResponseDTO studentResp =studentService.getStudent(id);

        if(studentResp ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);

    }
    //getall
    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
       List<CreateStudentResponseDTO> studentList =studentService.getAllStudent();

        return ResponseEntity.ok(studentList);

    }
    //update
    @PutMapping("/update")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam("id") Long id, @RequestBody UpdateStudentRequestDTO studentReq){
        UpdateStudentResponseDTO studentResp =studentService.updateStudent(id,studentReq);

        if(studentResp ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam("id") Long id){
        Boolean isDeleted =studentService.deleteStudent(id);

        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");

    }
    @PatchMapping("/delete-soft")
    public ResponseEntity<String> soft_deleteStudent(@RequestParam Long id){
        Boolean isDeleted =studentService.soft_deleteStudent(id);

        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");

    }


}
