package com.interview.questions.interview.common.soap.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import tempconvert.wsdl.CelsiusToFahrenheit;
import tempconvert.wsdl.CelsiusToFahrenheitResponse;

/**
 * A soap client that provides abstraction to the temperature API
 */
public class WeatherClientService extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(WeatherClientService.class);

    @Value("${temparature_soap_action}")
    private String soapAction;
    @Value("${temparature_payload_url}")
    public String payLoadUrl;

    /**
     * Converts celsius to fahrenheit
     *
     * @param celsius
     * @return
     */
    public String celsiusToFahrenheit(String celsius) {
        String fahrenheit = "";
        CelsiusToFahrenheitResponse response;

        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius(celsius);

        try {
            response = (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(payLoadUrl, request, new SoapActionCallback(soapAction));
            fahrenheit = response.getCelsiusToFahrenheitResult();
        } catch (Exception e) {
            log.error("celsiusToFahrenheit", e);
        }

        return fahrenheit;
    }
}
