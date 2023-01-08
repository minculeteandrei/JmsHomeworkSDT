package com.example.jmshw;

import com.example.jmshw.domain.Condition;
import com.example.jmshw.domain.EmergencyUnit;
import com.example.jmshw.domain.FamilyDoctor;
import com.example.jmshw.repositories.PatientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicSession;

@SpringBootApplication
public class JmsHwApplication {
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
//        JmsTemplate template = new JmsTemplate();
//        template.setMessageConverter(converter);
//        template.setConnectionFactory(connectionFactory);
//        template.setPubSubDomain(true);
//        return template;
//    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JmsHwApplication.class, args);

        JmsTemplate jmsTemplateTopic = new JmsTemplate();
        jmsTemplateTopic.setMessageConverter(context.getBean(MessageConverter.class));
        jmsTemplateTopic.setConnectionFactory(context.getBean(ConnectionFactory.class));
        jmsTemplateTopic.setPubSubDomain(true);

        EmergencyUnit emergencyUnit = new EmergencyUnit();
        emergencyUnit.send(jmsTemplateTopic, "Andrei", Condition.LOW_HURT);
    }
}
