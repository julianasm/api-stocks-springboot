package com.example.apistockspringboot;


import com.example.apistockspringboot.dto.StocksHistoricPricesDto;
import com.example.apistockspringboot.repository.StocksHistoricPricesRepository;
import com.example.apistockspringboot.resources.StocksHistoricResources;
import com.example.apistockspringboot.resources.StocksResources;
import com.example.apistockspringboot.service.StockService;
import com.example.apistockspringboot.service.StocksHistoricPricesService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest
public class StocksHistoricRepositoryTest {

    @Autowired
    StocksHistoricResources stocksHistoricResources;

    @MockBean
    StocksHistoricPricesRepository stocksHistoricPricesRepository;

    @MockBean
    StocksHistoricPricesService stocksHistoricPricesService;

    @MockBean
    StockService stockService;

    @BeforeEach
    public void setUp(){
        RestAssuredMockMvc.standaloneSetup(this.stocksHistoricResources);
    }

    @Test
    public void RetornarHistoricoStocks() {
        when(this.stocksHistoricResources.historicPrices(9L)).
        thenReturn(new ArrayList<StocksHistoricPricesDto>().stream().toList());

        RestAssuredMockMvc.given().accept(ContentType.JSON).when()
                .get("chart/{id}", 9L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
