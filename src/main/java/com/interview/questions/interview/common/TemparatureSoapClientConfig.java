package com.interview.questions.interview.common;

import com.interview.questions.interview.common.soap.support.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class TemparatureSoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("tempconvert.wsdl");
        return marshaller;
    }

    @Bean
    public WeatherService weatherSoapClient(Jaxb2Marshaller marshaller) {
        WeatherService client = new WeatherService();
        client.setDefaultUri("https://www.w3schools.com/xml/tempconvert.asmx?WSDL");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
