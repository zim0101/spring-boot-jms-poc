package com.app.jmspoc;

import com.app.jmspoc.model.Product;
import com.app.jmspoc.repository.ProductRepository;
import com.app.jmspoc.service.jms.publisher.SalesReportGenerationPublisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class JmsPOCApplication implements CommandLineRunner {

	private final SalesReportGenerationPublisher salesReportGenerationPublisher;
	private final ProductRepository productRepository;

    public JmsPOCApplication(SalesReportGenerationPublisher salesReportGenerationPublisher, ProductRepository productRepository) {
        this.salesReportGenerationPublisher = salesReportGenerationPublisher;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(JmsPOCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		product.setName("ABC");
		product.setPrice("10");
		productRepository.save(product);
		salesReportGenerationPublisher.publishMessage(product);
	}
}
