package com.bakaev.controllers;

import com.bakaev.helper.Functions;
import com.bakaev.models.SchoolSubjects;
import com.bakaev.models.Students;
import com.bakaev.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    Functions funs;

    @GetMapping("/allStudents")
    public String main(Model model) throws IOException {
        List<Students> students = studentsRepository.findAll();
        model.addAttribute("students", students);
        return "allStudents";
    }

    @GetMapping("/allClassAvg")
    public String avg(Model model) {
        return funs.getAVGInClasses(model);
    }

    @GetMapping("/findStudentsOneClass/{clazz}")
    public String getStudentsInClass(@PathVariable(value = "clazz") int clazz, Model model) {
        Iterable<Students> students = studentsRepository.findAllStudentsByClazz(clazz);
        model.addAttribute("students", students);
        return "allStudentInClass";
    }

    @GetMapping("/gradesStudent/{id}")
    public String getGradesStudent(@PathVariable(value = "id") Long id, Model model) {
        Students students = studentsRepository.findStudentById(id);
        List listGrades = new ArrayList();
        listGrades.addAll(students.getSchoolSubjects());
        model.addAttribute("subjects", listGrades);
        return "gradesStudent";
    }
}
