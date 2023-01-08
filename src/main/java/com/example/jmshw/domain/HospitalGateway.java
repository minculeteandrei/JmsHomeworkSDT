package com.example.jmshw.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class HospitalGateway {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "NewPatientTopic", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        System.out.println(message.toString());

        jmsTemplate.convertAndSend("InquireQueue", message.getName(), message1 -> {
            message1.setJMSCorrelationID("ResponseQueue");
            return message1;
        });
    }

    @JmsListener(destination = "ResponseQueue")
    public void receiveDataFromDb(String data) {
        System.out.println("the data is: " + data);
    }
}
