package com.example.jmshw.domain;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class FamilyDoctor {

    @JmsListener(destination = "NewPatientTopic", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        System.out.println(message.getName() + " is in danger. It's condition is " + message.getCondition());
    }
}
