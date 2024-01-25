package com.app.jmspoc.service.business;

import com.app.jmspoc.repository.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }
}