package com.bcncgroup.test.app.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bcncgroup.test.app.businesslogic.models.Price;
import com.bcncgroup.test.app.businesslogic.repositories.PriceRepository;
import com.bcncgroup.test.app.businesslogic.services.PriceService;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    private PriceService priceService = new PriceService(priceRepository);

    Price price1, price2;
    List<Price> list;

    @BeforeEach
    public void setup() {
        price1 = new Price();
        price1.setPrice_list(1);
        price1.setBrand_id(1);
        price1.setStart_date("2020-06-14 00:00:00.000");
        price1.setEnd_date("2020-12-31 23:59:59.000");
        price1.setProduct_id(35455);
        price1.setPriority(0);
        price1.setPrice(35.50f);
        price1.setCurr("EUR");

        price2 = new Price();
        price2.setPrice_list(4);
        price2.setBrand_id(1);
        price2.setStart_date("2020-06-15 16:00:00.000");
        price2.setEnd_date("2020-12-31 23:59:59.000");
        price2.setProduct_id(35455);
        price2.setPriority(1);
        price2.setPrice(38.95f);
        price2.setCurr("EUR");

        list = new ArrayList<>();
        list.add(price1);
        list.add(price2);
    }

    @Test
    void mockedComponentsAreNotNull() {
        assertNotNull(priceRepository);
        assertNotNull(priceService);
    }

    @Test
    void getMaximumPriorityPrice_withWrongParams() {
        when(priceRepository.findByProductBrandAndDate(1, 1, "2020-12-31 00:00:00.000")).thenReturn(new ArrayList<>());
        List<Price> priceList = priceRepository.findByProductBrandAndDate(1, 1, "2020-12-31 00:00:00.000");
        Optional<Price> find = priceService.getMaximumPriorityPrice(priceList);
        assertEquals(false, find.isPresent());
    }

    @Test
    void getMaximumPriorityPrice_withGoodParams() {
        when(priceRepository.findByProductBrandAndDate(35455, 1, "2020-12-31 00:00:00.000"))
                .thenReturn(list);
        List<Price> priceList = priceRepository.findByProductBrandAndDate(35455, 1, "2020-12-31 00:00:00.000");
        Optional<Price> find = priceService.getMaximumPriorityPrice(priceList);

        assertEquals(true, find.isPresent());

        Price price = find.get();

        assertNotNull(price);

        assertEquals(4, price.getPrice_list());
        assertEquals(35455, price.getProduct_id());
        assertEquals(1, price.getBrand_id());
        assertEquals(1, price.getPriority());
        assertEquals(38.95, price.getPrice(), 0.00001);
        assertEquals("EUR", price.getCurr());
        assertEquals("2020-06-15 16:00:00.000", price.getStart_date());
        assertEquals("2020-12-31 23:59:59.000", price.getEnd_date());
    }
}
