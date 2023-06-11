package com.bcncgroup.test.app.application;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcncgroup.test.app.application.dtos.request.GetPriceDTO;
import com.bcncgroup.test.app.application.dtos.response.GetPriceResponse;
import com.bcncgroup.test.app.businesslogic.models.Price;
import com.bcncgroup.test.app.businesslogic.services.PriceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping(path = "/get", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetPriceResponse> getPriceByProductBrandAndDate(
            @Valid @RequestBody GetPriceDTO data) {
        Optional<Price> price = null;
        try {
            price = priceService.findByProductBrandAndDate(data.getProduct_id(), data.getBrand_id(),
                    data.getDate());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return price.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new GetPriceResponse(price.get()))
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
