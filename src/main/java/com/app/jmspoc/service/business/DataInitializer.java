package com.app.jmspoc.service.business;

import com.app.jmspoc.model.Product;
import com.app.jmspoc.model.Sale;
import com.app.jmspoc.repository.ProductRepository;
import com.app.jmspoc.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public DataInitializer(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void run(String... args) {
        generateProducts();
        generateSales();
    }

    private void generateProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(String.valueOf(generateRandomPrice()));
            products.add(product);
        }

        productRepository.saveAll(products);
    }

    private void generateSales() {
        List<Product> allProducts = productRepository.findAll();
        Random random = new Random();

        // Set up start and end dates for the year 2024
        Calendar startDate = Calendar.getInstance();
        startDate.set(2024, Calendar.JANUARY, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2025, Calendar.JANUARY, 1);

        // Iterate through each day of 2024
        for (Date date = startDate.getTime(); startDate.before(endDate); startDate.add(Calendar.DATE, 1), date = startDate.getTime()) {
            // Create a sale with the current date
            Sale sale = new Sale();
            Product product = allProducts.get(random.nextInt(allProducts.size()));
            sale.setProduct(product);
            sale.setQuantity(random.nextInt(5) + 1);
            sale.setPrice(product.getPrice());
            sale.setCreatedAt(date); // Set the specific date for this sale
            saleRepository.save(sale);
        }
    }

    private double generateRandomPrice() {
        return (int) (Math.random() * (100 - 10) + 10); // Random number between 10 and 100
    }
}