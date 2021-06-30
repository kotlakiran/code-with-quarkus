package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


@ApplicationScoped
public class SQSProcessor implements Processor{
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());
        System.out.println(exchange.getFromRouteId());
        System.out.println(exchange.getIn().getMessageId());
    }
}
