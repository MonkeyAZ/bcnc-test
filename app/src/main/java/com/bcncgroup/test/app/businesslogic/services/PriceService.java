package com.bcncgroup.test.app.businesslogic.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcncgroup.test.app.businesslogic.models.Price;
import com.bcncgroup.test.app.businesslogic.repositories.PriceRepository;

@Service
public class PriceService {

    @Autowired
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findByProductBrandAndDate(int product_id, int brand_id, String date) {
        List<Price> find = this.priceRepository.findByProductBrandAndDate(
                product_id, brand_id, date);
        return getMaximumPriorityPrice(find);
    }

    public Optional<Price> getMaximumPriorityPrice(List<Price> prices) {
        return prices.stream().max(Comparator.comparing(Price::getPriority));
    }

}
