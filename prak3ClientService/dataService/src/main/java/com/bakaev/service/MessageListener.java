package com.bakaev.service;

import com.bakaev.models.SchoolSubjects;
import com.bakaev.models.Students;
import com.bakaev.repositories.SchoolSubjectRepository;
import com.bakaev.repositories.StudentsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    SchoolSubjectRepository schoolSubjectRepository;

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenerStudent(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Students student = mapper.readValue(data, Students.class);
        studentsRepository.save(student);
    }

    @KafkaListener(topics = "new_topic2", containerFactory = "kafkaListenerContainerFactory")
    public void listenerSubject(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SchoolSubjects subjects = mapper.readValue(data, SchoolSubjects.class);
        Students student = studentsRepository.findStudentById(subjects.getId_stud());
        subjects.setStudent(student);
        student.getSchoolSubjects().add(subjects);
        studentsRepository.save(student);
        schoolSubjectRepository.save(subjects);
    }
}
