package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.dto.GetAllStocksDto;
import com.example.apistockspringboot.dto.StockInfoDto;
import com.example.apistockspringboot.dto.StockPricesDto;
import com.example.apistockspringboot.handleerror.NotFoundException;
import com.example.apistockspringboot.service.StockService;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:8080/", "http://localhost:8081/"})
@RestController
@RequiredArgsConstructor
public class StocksResources {


    private final StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<List<GetAllStocksDto>> listStocks(){
        return ResponseEntity.ok().body(stockService.listByUpdate());
    }


    @GetMapping("stock-info/{id}")
    public ResponseEntity<StockInfoDto> getStockInfo(@PathVariable(value="id") Long id) throws NotFoundException, InterruptedException {
        Thread.sleep(3000);
        try {
            return ResponseEntity.ok().body(stockService.getStockInfo(id));
        } catch (NotFoundException e){
            if(e.getMessage().equals("STOCK_NOT_FOUND")){
                return ResponseEntity.notFound().build();
            }
        } return ResponseEntity.badRequest().build();
    }


    @PostMapping("/update_stocks")
    public ResponseEntity<StockPricesDto> updateStocks(
            @Valid @RequestBody StockPricesDto stocksPricesDto) throws ResourceNotFoundException {
       return stockService.updateStocks(stocksPricesDto);
    }


    @GetMapping(value =  "/subscribe")
    public SseEmitter subscribe(HttpServletResponse response){
        return stockService.subscribe(response);
    }


}
