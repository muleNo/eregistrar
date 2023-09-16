package edu.mum.cs.cs425.eregistrar.controller;

import edu.mum.cs.cs425.eregistrar.model.Student;
import edu.mum.cs.cs425.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eregistrar")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @GetMapping("/student/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/student")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/eregistrar/students";
    }

    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/student/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        studentService.save(student); 
        return "redirect:/eregistrar/students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/eregistrar/students";
    }
}
