package com.example.apistockspringboot;

import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.repository.StocksHistoricPricesRepository;
import com.example.apistockspringboot.service.StockService;
import com.example.apistockspringboot.service.StocksHistoricPricesService;
import com.example.apistockspringboot.repository.StocksRepository;
import com.example.apistockspringboot.resources.StocksResources;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest
@RequiredArgsConstructor
 class StocksResourceTest {

    @Autowired
    private StocksResources stocksResources;

    @MockBean
    private StockService stockService;

    @MockBean
    private StocksRepository stocksRepository;

    @MockBean
    private StocksHistoricPricesService stocksHistoricPricesService;

    @MockBean
    private StocksHistoricPricesRepository stocksHistoricPricesRepository;

    @BeforeEach
    public void setUp(){
        RestAssuredMockMvc.standaloneSetup(this.stocksResources);
    }

    @Test
    void RetornarStockPorId() throws Exception {
        when(this.stocksRepository.findById(5L)).
        thenReturn(java.util.Optional.of(new Stocks(5L, 30D, 30D)));


        RestAssuredMockMvc.given().accept(ContentType.JSON).when()
                .get("stock-info/{id}", 5L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void RetornarTodosStocks() {
        when(this.stocksRepository.findAllOrderByUpdate()).thenReturn(new ArrayList<Stocks>().stream().toList());

        RestAssuredMockMvc.given().accept(ContentType.JSON).when()
                .get("/stocks")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
