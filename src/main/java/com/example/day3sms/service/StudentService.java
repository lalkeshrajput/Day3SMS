package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService (StudentRepository repository){
        this.repository = repository;
    }

    //  Create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

    // Display
    public List<StudentModel> getAllStudents() {
        return repository.findAll();
    }

    // Update
    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("no student found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }

    // Delete
    public void deleteStudent(String id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Student not found");
        }
        repository.deleteById(id);
    }






}
