package com.springboot.springbootrestapi.controller;

import com.springboot.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("student")//http://localhost:8080/student
    public Student getStudent() {
        Student student = new Student(
          1,"Kristine", "Veneles"
        );
        return student;
    }
    @GetMapping("students")//http://localhost:8080/students
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kristine", "Veneles"));
        students.add(new Student(2, "Scott", "Shonk"));
        students.add(new Student(3, "Katrina", "Bernardo"));
        students.add(new Student(4, "Sanjay", "Pawar"));
        return students;
    }

    //Spring Boot rest api with Path Variable
    //{id} - URI template variable
    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId) {
        return new Student(studentId, "Kristine", "Veneles");
    }
    //Spring Boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Kristine&lastName=Veneles //query parameter
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }
    //Spring boot rest api that handles http post request;j @PostMapping, @RequestBody
    //Requestbody: json -> java object
    //create a new resource
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //Spring Boot RestAPI that handles http PUT request  updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //SpringBoot RESTAPI handles HTTP DELETE - delete existing resource
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return "Student deleted successfully";
    }

}
