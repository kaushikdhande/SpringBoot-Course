package com.example.dto_demo.service;

import com.example.dto_demo.dto.CreateStudentRequestDTO;
import com.example.dto_demo.dto.CreateStudentResponseDTO;
import com.example.dto_demo.dto.UpdateStudentRequestDTO;
import com.example.dto_demo.dto.UpdateStudentResponseDTO;
import com.example.dto_demo.entity.Student;
import com.example.dto_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReqDto) {
    Student student=mapToEntity(studentReqDto);
    student.setCreatedAt(LocalDateTime.now());
    student.setUpdatedAt(LocalDateTime.now());
    Student studentResp=studentRepository.save(student);
    return mapToDto(studentResp);
    }

       public CreateStudentResponseDTO getStudent(Long id) {
        Optional<Student> studentResp=studentRepository.findById(id);
        if(studentResp.isPresent()){
            return mapToDto(studentResp.get());
        }
        return null;
    }
        public List<CreateStudentResponseDTO> getAllStudent() {
        List<Student> studentList=studentRepository.findByDeletedIsFalse();
        return studentList.stream()
                .map(this::mapToDto).toList();
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq) {
        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            return null;
        }
       Student studentToSave= existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());
        Student savedStudent =studentRepository.save(studentToSave);
        return mapToUpdateDto(savedStudent);

    }



    public Boolean deleteStudent(Long id) {
    boolean isDeleted=studentRepository.existsById(id);
    if(!isDeleted) return false;
    studentRepository.deleteById(id);
    return true;
    }

    public Boolean soft_deleteStudent(Long id) {
        Optional<Student> existingStudent=studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
        return false;
        }
        Student studentToSave=existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
        return  true;
    }

    private Student mapToEntity(CreateStudentRequestDTO studentReqDto) {
        Student student =new Student();
        student.setName(studentReqDto.getName());
        student.setAge(studentReqDto.getAge());
        student.setEmail(studentReqDto.getEmail());
        student.setRollNo(studentReqDto.getRollNo());
        student.setSubject(studentReqDto.getSubject());
        student.setDeleted(false);
        return student;
    }

    private CreateStudentResponseDTO mapToDto(Student student){
        CreateStudentResponseDTO responseDTO=new CreateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student  save successfully");
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getCreatedAt());

        return responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDto(Student student) {
        UpdateStudentResponseDTO responseDTO=new UpdateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student update successfully");
        responseDTO.setUpdatedAt(student.getCreatedAt());

        return responseDTO;
    }

}
