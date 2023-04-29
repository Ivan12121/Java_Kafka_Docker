package com.bakaev.controllers;

import com.bakaev.api.ApiClient;
import com.bakaev.models.SchoolSubjects;
import com.bakaev.models.Students;
import com.bakaev.service.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;


@Controller
public class MainController {
    @Autowired
    MessageProducer messageProducer;
    @Autowired
    ApiClient apiClient;

    @GetMapping("/")
    public String osnova(Model model) {
        return "osnova";
    }

    @GetMapping("/addStud")
    public String addStud(Model model) {
        return "addStud";
    }

    @GetMapping("/addPred")
    public String addPred(Model model) {
        return "addPred";
    }

    @GetMapping("/allStudents")
    public String getAllStudents(Model model) throws IOException {
        model.addAttribute("str", apiClient.getTemplate("http://service:8082/allStudents"));
        return "allStudents";
    }

    @GetMapping("/allClassAVG")
    public String getAVGStudents(Model model) {
        model.addAttribute("str", apiClient.getTemplate("http://service:8082/allClassAvg"));
        return "allClassAVG";
    }

    @GetMapping("/findStudentsOneClass/{clazz}")
    public String findStudentsInClass(@PathVariable(value = "clazz") int clazz, Model model) {
        model.addAttribute("str", apiClient.getTemplateWithPathVariable("http://service:8082/findStudentsOneClass/{clazz}", clazz));
        return "allStudentInClass";
    }

    @GetMapping("/gradesStudent/{id}")
    public String getGradesStudent(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("str", apiClient.getTemplateWithPathVariable("http://service:8082/gradesStudent/{id}", Math.toIntExact(id)));
        return "gradesStudent";
    }

    @PostMapping("/addStud")
    public String addPostStudent(@RequestParam String name, @RequestParam String surname, @RequestParam int age, @RequestParam int clazz, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Students students = new Students(name + " " + surname, age, clazz);
        String jsonStudent = mapper.writeValueAsString(students);
        messageProducer.sendMessage(jsonStudent);
        return "addStud";
    }

    @PostMapping("/addPred")
    public String addPostSubject(@RequestParam String fullName, @RequestParam int grade, @RequestParam int idStudent, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SchoolSubjects subjects = new SchoolSubjects(fullName, grade, idStudent);
        String jsonSubject = mapper.writeValueAsString(subjects);
        messageProducer.sendSubject(jsonSubject);
        return "addPred";
    }
}
