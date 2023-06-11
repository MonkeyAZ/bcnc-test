package com.bcncgroup.test.app.application.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class GetPriceDTO {
    @Min(value = 1, message = "Product ID must be a valid integer greater than 0")
    private int product_id;

    @Min(value = 1, message = "Brand ID must be a valid integer greater than 0")
    private int brand_id;

    @NotBlank(message = "Date field cannot be empty and it must have yyyy-MM-dd HH:mm:ss format")
    @JsonFormat(pattern = "Yyyy-mm-dd HH:mm:ss")
    private String date;

    public GetPriceDTO() {
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getBrand_id() {
        return this.brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
