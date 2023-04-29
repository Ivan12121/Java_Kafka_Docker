package com.bakaev.helper;

import com.bakaev.models.SchoolSubjects;
import com.bakaev.models.Students;
import com.bakaev.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Functions {
    @Autowired
    StudentsRepository studentsRepository;

    public String getAVGInClasses(Model model) {
        Iterable<Students> students11Cl = studentsRepository.findAllStudentsByClazz(11);
        Iterable<Students> students10Cl = studentsRepository.findAllStudentsByClazz(10);
        Iterable<Students> students9Cl = studentsRepository.findAllStudentsByClazz(9);
        Iterable<Students> students8Cl = studentsRepository.findAllStudentsByClazz(8);

        int avgoo = 0;
        int sumoo = 0;

        int avgoz = 0;
        int sumoz = 0;

        int avgn = 0;
        int sumn = 0;

        int avge = 0;
        int sume = 0;

        for (Students oneone : students11Cl) {
            for (SchoolSubjects ss : oneone.getSchoolSubjects()) {
                sumoo += ss.getGrade();
                avgoo = sumoo / oneone.getSchoolSubjects().size();
            }
        }

        for (Students oneone : students10Cl) {
            for (SchoolSubjects ss : oneone.getSchoolSubjects()) {
                sumoz += ss.getGrade();
                avgoz = sumoz / oneone.getSchoolSubjects().size();
            }
        }

        for (Students oneone : students9Cl) {
            for (SchoolSubjects ss : oneone.getSchoolSubjects()) {
                sumn += ss.getGrade();
                avgn = sumn / oneone.getSchoolSubjects().size();
            }
        }

        for (Students oneone : students8Cl) {
            for (SchoolSubjects ss : oneone.getSchoolSubjects()) {
                sume += ss.getGrade();
                avge = sume / oneone.getSchoolSubjects().size();
            }
        }

        model.addAttribute("avg11", avgoo);
        model.addAttribute("avg10", avgoz);
        model.addAttribute("avg9", avgn);
        model.addAttribute("avg8", avge);
        return "allClassAVG";
    }

}
