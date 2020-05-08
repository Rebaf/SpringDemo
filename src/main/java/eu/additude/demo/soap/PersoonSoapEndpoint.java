package eu.additude.demo.soap;

import eu.additude.demo.controller.PersoonService;
import eu.additude.guides.gs_producing_web_service.GetPersonenResponse;
import eu.additude.guides.gs_producing_web_service.GetPersoonRequest;
import eu.additude.guides.gs_producing_web_service.GetPersoonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PersoonSoapEndpoint {
    public static final String NAMESPACE_URI = "http://additude.eu/guides/gs-producing-web-service";

    @Autowired
    private PersoonService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersoonRequest")
    @ResponsePayload // Maak er een SOAP bericht van
    public GetPersoonResponse getPersoon(@RequestPayload GetPersoonRequest request) {
        System.out.println("LOG- personenSoapWS/" + request.getId() + " - Aanroep van onze webserivce voor het opvragen van één persoon.");
        GetPersoonResponse response = new GetPersoonResponse();
        response.setPersoon(service.findPersoonSOAPById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonenRequest") // Op basis van de getPersonenRequest uit het xml bericht wordt de mapping gedaan.
    @ResponsePayload
    public GetPersonenResponse getPersonen() {  // @RequestPayload GetPersonenRequest request: Geen parameters nodig, want we doen toch niets met het GetPersonenRequest. Deze wordt alleen gebruikt voor de mapping (vanuit het XML bericht)
        System.out.println("LOG- personenSoapWS/ - Aanroep van onze webserivce voor het opvragen van alle personen.");
        GetPersonenResponse response = new GetPersonenResponse();
        response.getPersonen().addAll(service.getAllePersonenSOAP());
        return response;
    }
}