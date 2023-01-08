package com.example.jmshw.domain;

import org.springframework.jms.core.JmsTemplate;

public class EmergencyUnit {
    public void send(JmsTemplate jmsTemplate, String name, Condition condition) {
        jmsTemplate.convertAndSend("NewPatientTopic", new Message(name, condition));
    }
}
