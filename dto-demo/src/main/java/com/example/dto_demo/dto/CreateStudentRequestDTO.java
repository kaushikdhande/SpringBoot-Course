package com.example.dto_demo.dto;


import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2, max = 50 ,message = "Student must be within 2 to 50 character long")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 18,message = "Student must be atleast 18 years old")
    private Integer age;
    @NotBlank(message = "Student  email cannot be blank")
    @Email
    private String email;

    @NotNull(message = "RollNo is Required")
    private Integer rollNo;
    @NotBlank(message = "Subject is required")
    private String subject;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
