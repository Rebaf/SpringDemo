package eu.additude.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/personenSoapWS/*");
    }

    @Bean
    public XsdSchema personenSchema() {
        return new SimpleXsdSchema(new ClassPathResource("personen.xsd"));
    }

    @Bean(name = "personenSoap") // Dit wordt de naam van de wsdl die je naar buiten vrij geeft.
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema personenSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(personenSchema);
        definition.setLocationUri("/personenSoapWS");
        definition.setPortTypeName("PersonenPort");
        definition.setTargetNamespace("http://additude.eu/guides/gs-producing-web-service");
        return definition;
    }
}