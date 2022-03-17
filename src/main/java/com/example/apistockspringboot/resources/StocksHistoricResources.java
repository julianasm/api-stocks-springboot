package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.dto.StocksHistoricPricesDto;
import com.example.apistockspringboot.service.StocksHistoricPricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequiredArgsConstructor
public class StocksHistoricResources {

    private final StocksHistoricPricesService stocksHistoricPricesService;

    @GetMapping("chart/{id}")
    public List<StocksHistoricPricesDto> historicPrices(@PathVariable("id") Long idStock){
       return stocksHistoricPricesService.historicPrices(idStock);
    }
}
