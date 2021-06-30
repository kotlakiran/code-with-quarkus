package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@ApplicationScoped
public class SQSRouteBuilder extends RouteBuilder {
    @Autowired
    private SQSProcessor sqsProcessor;

    @Value("${sqs.queue:demo}")
    private String queueName;

    private String uriFormat="aws2-sqs://%s?amazonSQSClient=#client";
    @Override
    public void configure() throws Exception {
        System.out.println("CamelContext routers about to add.");
        // TODO Auto-generated method stub
        String uri = String.format(uriFormat, queueName);

        from(uri).process(sqsProcessor);
    }
}
