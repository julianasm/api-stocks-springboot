package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.ServiceStock.StockPricesDto;
import com.example.apistockspringboot.ServiceStock.StockService;
import com.example.apistockspringboot.repository.StocksRepository;
import com.example.apistockspringboot.models.Stocks;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

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

    @GetMapping("/stock-info/{id}")
    public Optional<Stocks> listStockInfoUnique(@PathVariable(value="id") Long id) throws Exception{

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
        if (stocksDto.getBid_min() != null) {
            stocks.setBid_min(stocksDto.getBid_min());
        }
        if (stocksDto.getBid_max() != null) {
            stocks.setBid_max(stocksDto.getBid_max());
        }
        if (stocksDto.getAsk_min() != null){
            stocks.setAsk_min(stocksDto.getAsk_min());
        }
        if (stocksDto.getAsk_max() != null){
            stocks.setAsk_max(stocksDto.getAsk_max());
        }
        return new ResponseEntity<>(stocksRepository.save(stocks), HttpStatus.OK);
    }

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @CrossOrigin
    @RequestMapping(value =  "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestHeader("Authorization") String token){
        SseEmitter sseEmitter = new SseEmitter();
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e){
            e.printStackTrace();
        }
        emitters.add(sseEmitter);
        return sseEmitter;
    }
}
