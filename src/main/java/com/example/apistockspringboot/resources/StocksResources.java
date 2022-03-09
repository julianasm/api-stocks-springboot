package com.example.apistockspringboot.resources;

import com.example.apistockspringboot.Dto.StockPricesDto;
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

    private final StocksRepository stocksRepository;

    private final StocksHistoricPricesService stocksHistoricPricesService;

    private final StockService stockService;

    @CrossOrigin
    @GetMapping("/stocks")
    public List<Stocks> listStocks(){
        return stocksRepository.findAllOrderByUpdate();
    }

    @CrossOrigin
    @GetMapping("/stocks/{id}")
    public Optional<Stocks> listStockUnique(@PathVariable(value="id") Long id) throws Exception{

        Thread.sleep(3000);

        return stocksRepository.findById(id);
    }

    @CrossOrigin
    @GetMapping("/stock-info/{id}")
    public Optional<Stocks> listStockInfoUnique(@PathVariable(value="id") Long id) throws Exception{

        Thread.sleep(3000);

        return stocksRepository.findById(id);
    }

    @CrossOrigin
    @PostMapping("/new_stock")
    public Stocks saveStock(@RequestBody Stocks stocks){
        return stocksRepository.save(stocks);
    }

    @CrossOrigin
    @PostMapping("/update_stocks")
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
        stocksRepository.save(stocks);
        System.out.println(stocksDto.getId());
        dispatchEventToClients();
        stocksHistoricPricesService.atualizarPrices(stocks);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @CrossOrigin
    @GetMapping(value =  "/subscribe")
    public SseEmitter subscribe(HttpServletResponse response){
        response.setHeader("Cache_control", "no-store");
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try {
            System.out.println("teste subscribe");
            emitters.add(sseEmitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion(() -> this.emitters.remove(sseEmitter));

        return sseEmitter;
    }


    public void dispatchEventToClients() {
        System.out.println(emitters.isEmpty());
        for (SseEmitter emitter: emitters){
            try {
                emitter.send(stocksRepository.findAllOrderByUpdate());
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
