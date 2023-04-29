package com.bakaev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${kafka.topic.name}")
    private String topicStudentName;
    private String topicSubjectName = "new_topic2";


    public void sendMessage(String data) {
        kafkaTemplate.send(topicStudentName, data);
    }
    public void sendSubject(String data) {
        kafkaTemplate.send(topicSubjectName, data);
    }
}
