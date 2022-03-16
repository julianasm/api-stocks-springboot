package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.dto.StockPricesDto;
import com.example.apistockspringboot.service.StockService;
import com.example.apistockspringboot.service.StocksHistoricPricesService;
import com.example.apistockspringboot.repository.StocksRepository;
import com.example.apistockspringboot.models.Stocks;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class StocksResources {


    private final StockService stockService;

    @CrossOrigin
    @GetMapping("/stocks")
    public List<Stocks> listStocks(){
        return stockService.listByUpdate();
    }

    @CrossOrigin
    @GetMapping("/stock-info/{id}")
    public Optional<Stocks> listStockInfoUnique(@PathVariable(value="id") Long id) throws InterruptedException {

        Thread.sleep(3000);

        return stockService.listStockInfo(id);
    }


    @CrossOrigin
    @PostMapping("/update_stocks")
    public ResponseEntity<Stocks> updateStocks(
            @Valid @RequestBody StockPricesDto stocksDto) throws ResourceNotFoundException {
       return stockService.updateStocks(stocksDto);
    }


    @CrossOrigin
    @GetMapping(value =  "/subscribe")
    public SseEmitter subscribe(HttpServletResponse response){
        return stockService.subscribe(response);
    }


}
