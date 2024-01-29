package com.app.jmspoc.service.business;

import com.app.jmspoc.model.Sale;
import com.app.jmspoc.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> findByCreatedAt(Date createdAt) {
        return saleRepository.findByCreatedAt(createdAt);
    }

    public Double sumPricesByCreatedDate(Date createdAt) {
        return saleRepository.sumPricesByCreatedDate(createdAt);
    }
    public Double sumPricesBetweenDates(Date startDate, Date endDate) {
        return saleRepository.sumPricesByCreatedAtBetween(startDate, endDate);
    }
}