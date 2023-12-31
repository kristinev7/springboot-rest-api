package com.springboot.springbootrestapi.controller;

import com.springboot.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students") //base url
public class StudentController {
    @GetMapping("student")//http://localhost:8080/student
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
          1,"Kristine", "Veneles"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "ramesh").body(student);
    }
    @GetMapping//http://localhost:8080/students
    public ResponseEntity<List<Student>>getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Kristine", "Veneles"));
        students.add(new Student(2, "Scott", "Shonk"));
        students.add(new Student(3, "Katrina", "Bernardo"));
        students.add(new Student(4, "Sanjay", "Pawar"));
        //return students;
        return ResponseEntity.ok(students);
    }

    //Spring Boot rest api with Path Variable
    //{id} - URI template variable
    @GetMapping("{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId) {
        return new Student(studentId, "Kristine", "Veneles");
    }
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }
    //Spring Boot REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Kristine&lastName=Veneles //query parameter
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        //return new Student(id, firstName, lastName);
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }
    //Spring boot rest api that handles http post request;j @PostMapping, @RequestBody
    //Requestbody: json -> java object
    //create a new resource
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    //Spring Boot RestAPI that handles http PUT request  updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //return student;
        return ResponseEntity.ok(student);
    }
    //SpringBoot RESTAPI handles HTTP DELETE - delete existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        //return "Student deleted successfully";
        return ResponseEntity.ok("Student deleted successfully");
    }

}
