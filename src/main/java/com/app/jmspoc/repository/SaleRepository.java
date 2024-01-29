package com.app.jmspoc.repository;

import com.app.jmspoc.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByCreatedAt(Date createdAt);

    @Query("SELECT SUM(s.price) FROM Sale s WHERE s.createdAt = :createdDate")
    Double sumPricesByCreatedDate(@Param("createdDate") Date createdDate);

    @Query(value = "SELECT SUM(s.price) FROM Sale s WHERE s.createdAt >= :startDate AND s.createdAt <= :endDate")
    Double sumPricesByCreatedAtBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
