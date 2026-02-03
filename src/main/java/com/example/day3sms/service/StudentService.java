package com.example.day3sms.service;

import com.example.day3sms.DTO.StudentRequestDTO;
import com.example.day3sms.DTO.StudentResponseDTO;
import com.example.day3sms.exception.StudentNotFoundException;
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
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }
    public StudentResponseDTO addStudent(StudentRequestDTO dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);
        return new StudentResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getAge(),
            saved.getEmail()
        );

    }

    // Display
//    public List<StudentModel> getAllStudents(){
//        return repository.findAll();
//    }
    public List<StudentResponseDTO> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getEmail()
                ))
                .toList();
    }




    // Update
//    public StudentModel updateStudent(String id, StudentModel student){
//        StudentModel existingStudent = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("no student found"));
//
//        existingStudent.setName(student.getName());
//        existingStudent.setAge(student.getAge());
//        existingStudent.setEmail(student.getEmail());
//
//        return repository.save(existingStudent);
//    }
    public StudentResponseDTO updateStudent(String id,StudentResponseDTO student){
        StudentModel existingStudent=repository.findById(id)
                .orElseThrow(()->new RuntimeException("NO Student found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        StudentModel updated=repository.save(existingStudent);
        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }


    // Delete
//    public void deleteStudent(String id){
//        if(!repository.existsById(id)){
//            throw new RuntimeException("Student not found");
//        }
//        repository.deleteById(id);
//    }
    public void delete(String id){
        if(!repository.existsById(id)){
            throw new StudentNotFoundException("No student found with this id");
        }
        repository.deleteById(id);
    }






}
