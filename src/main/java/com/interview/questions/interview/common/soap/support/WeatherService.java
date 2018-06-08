package com.interview.questions.interview.common.soap.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import temp_convert.wsdl.CelsiusToFahrenheit;
import temp_convert.wsdl.CelsiusToFahrenheitResponse;

public class WeatherService extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    public void callWebService() {

        //search for operation name="xxx" and soap:operation soapAction
        //net example try out this
        //https://graphical.weather.gov/xml/SOAP_server/ndfdXMLserver.php?wsdl
        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius("10");

        CelsiusToFahrenheitResponse response = (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://www.w3schools.com/xml/tempconvert.asmx?WSDL",
                        request,
                        new SoapActionCallback("https://www.w3schools.com/xml/CelsiusToFahrenheit"));//soap action

        logger.info("We are getting the res=================" + response.getCelsiusToFahrenheitResult());

    }
}
