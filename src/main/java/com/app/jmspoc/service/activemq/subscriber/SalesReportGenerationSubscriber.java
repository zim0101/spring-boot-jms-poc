package com.app.jmspoc.service.jms.subscriber;

import com.app.jmspoc.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SalesReportGenerationSubscriber {

    private static final Logger log = LoggerFactory.getLogger(SalesReportGenerationSubscriber.class);

    @JmsListener(destination = "${jmspoc.queue.sales-report-generation}")
    public void subscribeMessage(Product product) {
        System.out.println("SalesReportGenerationSubscriber received product: " + product);
        log.info("---------------------------------------------------");
        log.info("SalesReportGenerationSubscriber received product: {}", product.getName());
        log.info("---------------------------------------------------");
    }
}