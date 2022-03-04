package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.Dto.StocksHistoricPricesDto;
import com.example.apistockspringboot.models.StocksHistoricPrices;
import com.example.apistockspringboot.repository.StocksHistoricPricesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class StocksHistoricResources {

    private final StocksHistoricPricesRepository stocksHistoricPricesRepository;

    @CrossOrigin
    @GetMapping("chart/{id}")
    public List<StocksHistoricPricesDto> historicPrices(@PathVariable("id") Long id_stock){
        System.out.println(id_stock);
        return stocksHistoricPricesRepository.findByIdStock(id_stock).stream().map((StocksHistoricPrices stocksHistoricPrices) -> new StocksHistoricPricesDto(stocksHistoricPrices)).toList();
    }
}
