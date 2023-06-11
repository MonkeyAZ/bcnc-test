package com.bcncgroup.test.app.application.dtos.response;

import com.bcncgroup.test.app.businesslogic.models.Price;

public class GetPriceResponse {
    private final int price_list;
    private final int product_id;
    private final int brand_id;
    private final String start_date;
    private final String end_date;
    private final float price;
    private final String curr;

    public GetPriceResponse(Price price) {
        this.price_list = price.getPrice_list();
        this.product_id = price.getProduct_id();
        this.brand_id = price.getBrand_id();
        this.start_date = price.getStart_date();
        this.end_date = price.getEnd_date();
        this.price = price.getPrice();
        this.curr = price.getCurr();
    }

    public int getPrice_list() {
        return this.price_list;
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public int getBrand_id() {
        return this.brand_id;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public float getPrice() {
        return this.price;
    }

    public String getCurr() {
        return this.curr;
    }

}
