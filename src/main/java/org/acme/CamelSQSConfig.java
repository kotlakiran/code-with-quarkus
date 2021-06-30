package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.aws.sqs.SqsComponent;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.annotation.Bean;

import software.amazon.awssdk.services.sqs.SqsClient;



@ApplicationScoped
public class CamelSQSConfig {
   
    @Inject
    SqsClient client;
    

    @Inject
    SQSRouteBuilder SQSRouteBuilder;

	
    @Produces
    public SpringCamelContext getCamelContext(SqsClient client) throws Exception {
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("client", client);
        SpringCamelContext camelContext = new SpringCamelContext();
        camelContext.setRegistry(registry);
        SqsComponent component = new SqsComponent();
        component.setCamelContext(camelContext);
        component.start();
        addRoutes(camelContext);
        return camelContext;
    }

    private void addRoutes(CamelContext camelContext) throws Exception {
        camelContext.addRoutes(SQSRouteBuilder);
    }
}
