package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.ServiceStock.StockPricesDto;
import com.example.apistockspringboot.ServiceStock.StockService;
import com.example.apistockspringboot.repository.StocksRepository;
import com.example.apistockspringboot.models.Stocks;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StocksResources {

    @Autowired
    StocksRepository stocksRepository;


    @GetMapping("/stocks")
    public List<Stocks> listStocks(){
        return stocksRepository.findAll();
    }

    @GetMapping("/stocks/{id}")
    public Optional<Stocks> listStockUnique(@PathVariable(value="id") Long id) throws Exception{

        Thread.sleep(3000);

        return stocksRepository.findById(id);
    }

    @PostMapping("/new_stock")
    public Stocks saveStock(@RequestBody Stocks stocks){
        return stocksRepository.save(stocks);
    }

    @PutMapping("/update_stocks")
    public ResponseEntity<Stocks> updateStocks(
            @Valid @RequestBody StockPricesDto stocksDto) throws ResourceNotFoundException {
        Stocks stocks = stocksRepository.findById(stocksDto.getId()).orElseThrow(Error::new);
        if (stocksDto.getBid_min_price() != null) {
            stocks.setBid_min(stocksDto.getBid_min_price());
        }
        if (stocksDto.getBid_max_price() != null) {
            stocks.setBid_max(stocksDto.getBid_max_price());
        }
        if (stocksDto.getAsk_min_price() != null){
            stocks.setAsk_min(stocksDto.getAsk_min_price());
        }
        if (stocksDto.getAsk_max_price() != null){
            stocks.setAsk_max(stocksDto.getAsk_max_price());
        }
        return new ResponseEntity<>(stocksRepository.save(stocks), HttpStatus.OK);
    }
}
