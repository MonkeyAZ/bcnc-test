package com.bcncgroup.test.app.businesslogic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int price_list;

    @Column
    private int brand_id;

    @Column
    private String start_date;

    @Column
    private String end_date;

    @Column
    private int product_id;

    @Column
    private int priority;

    @Column
    private float price;

    @Column
    private String curr;

    public Price() {
    }

    public int getPrice_list() {
        return this.price_list;
    }

    public void setPrice_list(int price_list) {
        this.price_list = price_list;
    }

    public int getBrand_id() {
        return this.brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurr() {
        return this.curr;
    }

    public void setCurr(String currency) {
        this.curr = currency;
    }

}
