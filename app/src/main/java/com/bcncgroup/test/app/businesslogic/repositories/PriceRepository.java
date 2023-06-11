package com.bcncgroup.test.app.businesslogic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bcncgroup.test.app.businesslogic.models.Price;

@EnableJpaRepositories
public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query("SELECT p FROM Price p WHERE p.product_id=?1 AND p.brand_id=?2 AND ?3>p.start_date AND ?3<p.end_date")
    List<Price> findByProductBrandAndDate(int product_id, int brand_id, String date);
}
