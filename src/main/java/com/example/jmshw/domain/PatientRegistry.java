package com.example.jmshw.domain;

import com.example.jmshw.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class PatientRegistry {

    @Autowired
    private JmsTemplate jmsTemplate;
    private final PatientService patientService;

    public PatientRegistry(PatientService patientService) {
        this.patientService = patientService;
    }

    @JmsListener(destination = "InquireQueue")
    public void receiveMessage(String name, @Header(JmsHeaders.CORRELATION_ID) String messageId) {
        System.out.println("I will search in the db for: " + name);
        Patient temp = this.patientService.getPatient(name);

        if (temp != null)
            jmsTemplate.convertAndSend(messageId, "here is the data from the db for " + name + " \n" + temp);
        else
            jmsTemplate.convertAndSend(messageId, "no data found in the db for " + name);
    }
}
