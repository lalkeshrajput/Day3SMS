package com.example.day3sms.controller;

import com.example.day3sms.DTO.StudentRequestDTO;
import com.example.day3sms.DTO.StudentResponseDTO;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //    Create function API {Add new Student}

//    public StudentModel addStudent(@RequestBody StudentModel student){
//        return service.addStudent(student);
//    }
    @PostMapping("/addstudent")
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO student){
        return service.addStudent(student);
    }

    //    Display Student
    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents(){
        return service.getAllStudents();
    }

    //    Update Student
//    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
//        return service.updateStudent(id, student);
//    }

    @PutMapping("/update/{id}")
    public StudentResponseDTO updateStudent(@PathVariable String id,@RequestBody StudentResponseDTO student){
        return service.updateStudent(id,student);
    }



    //    Delete Student
//    public String deleteStudent(@PathVariable String id){
//        service.deleteStudent(id);
//        return "Student deleted successfully";
//    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }


}
