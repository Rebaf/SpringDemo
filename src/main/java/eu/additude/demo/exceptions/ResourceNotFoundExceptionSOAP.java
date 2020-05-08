package eu.additude.demo.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

import static eu.additude.demo.soap.PersoonSoapEndpoint.NAMESPACE_URI;

@SoapFault(locale = "nl"
        , faultCode = FaultCode.CUSTOM
        , customFaultCode = "{" + NAMESPACE_URI + "}custom_fault"
//        , faultStringOrReason = "@faultString" // gebruik je deze niet, dan wordt de message van de Exception gebruikt
)
public class ResourceNotFoundExceptionSOAP extends RuntimeException {

    public ResourceNotFoundExceptionSOAP(String message) {
        super(message);
    }
}