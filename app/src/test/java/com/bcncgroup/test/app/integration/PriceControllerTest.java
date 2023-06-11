package com.bcncgroup.test.app.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.bcncgroup.test.app.AppApplication;
import com.bcncgroup.test.app.application.dtos.request.GetPriceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {
        @LocalServerPort
        private int port;

        private TestRestTemplate restTemplate = new TestRestTemplate();
        private ObjectMapper mapper = new ObjectMapper();

        private HttpHeaders headers = new HttpHeaders();

        private GetPriceDTO getPriceDTO;

        @BeforeEach
        public void setup() {
                getPriceDTO = new GetPriceDTO();

        }

        @Test
        public void testgetPrice_withEmptyParams() throws JSONException, JsonProcessingException {

                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(new HashMap<>()), headers);

                ResponseEntity<String> response = restTemplate.exchange(
                                createURLWithPort("/prices/get"),
                                HttpMethod.POST, entity, String.class);

                HashMap<String, Object> mapResponse = mapper.readValue(response.getBody(), HashMap.class);

                assertTrue(mapResponse.containsKey("errors"));
                assertTrue(mapResponse.get("errors").toString()
                                .contains("Product ID must be a valid integer greater than 0"));
                assertTrue(mapResponse.get("errors").toString()
                                .contains("Brand ID must be a valid integer greater than 0"));
                assertTrue(mapResponse.get("errors").toString()
                                .contains("Date field cannot be empty and it must have yyyy-MM-dd HH:mm:ss format"));
        }

        @Test
        public void testgetPrice_withWrongParams() throws JSONException, JsonProcessingException {

                getPriceDTO.setProduct_id(1);
                getPriceDTO.setBrand_id(1);
                getPriceDTO.setDate("2020-12-30 00:00:00.000");

                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(getPriceDTO), headers);

                ResponseEntity<String> response = restTemplate.exchange(
                                createURLWithPort("/prices/get"),
                                HttpMethod.POST, entity, String.class);

                assertNull(response.getBody());
                assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
        }

        @Test
        public void testgetPrice_withGoodParams() throws JSONException, JsonProcessingException {

                getPriceDTO.setProduct_id(35455);
                getPriceDTO.setBrand_id(1);
                getPriceDTO.setDate("2020-12-30 00:00:00.000");

                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(getPriceDTO), headers);

                ResponseEntity<String> response = restTemplate.exchange(
                                createURLWithPort("/prices/get"),
                                HttpMethod.POST, entity, String.class);

                assertNotNull(response.getBody());
                assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        }

        private String createURLWithPort(String uri) {
                return "http://localhost:" + port + uri;
        }

        @Test
        @SqlGroup({
                        @Sql(value = "/drop_table.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
                        @Sql(value = "/create_table.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
        })
        public void testGetPrice_withDatabaseDown() throws JSONException, JsonProcessingException {
                getPriceDTO.setProduct_id(35455);
                getPriceDTO.setBrand_id(1);
                getPriceDTO.setDate("2020-12-30 00:00:00.000");

                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(getPriceDTO), headers);

                ResponseEntity<String> response = restTemplate.exchange(
                                createURLWithPort("/prices/get"),
                                HttpMethod.POST, entity, String.class);

                assertNull(response.getBody());
                assertTrue(response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR));
        }
}
