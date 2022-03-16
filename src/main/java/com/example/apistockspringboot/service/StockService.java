package com.example.apistockspringboot.service;

import com.example.apistockspringboot.dto.StockPricesDto;
import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.repository.StocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
@RequiredArgsConstructor
public class StockService {

    private final StocksRepository stocksRepository;

    private StocksHistoricPricesService stocksHistoricPricesService;


    public Stocks salvar(Stocks stocks) {
        return stocksRepository.save(stocks);
    }

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    public Optional<Stocks> listStockInfo(Long id){
        return stocksRepository.findById(id);
    }

    public List<Stocks> listByUpdate(){
        return stocksRepository.findAllOrderByUpdate();
    }


    public ResponseEntity<Stocks> updateStocks(StockPricesDto stocksDto){
        Stocks stocks = stocksRepository.findById(stocksDto.getId()).orElseThrow(Error::new);
        if (stocksDto.getBidMin() != null) {
            stocks.setBidMin(stocksDto.getBidMin());
        }
        if (stocksDto.getBidMax() != null) {
            stocks.setBidMax(stocksDto.getBidMax());
        }
        if (stocksDto.getAskMin() != null){
            stocks.setAskMin(stocksDto.getAskMin());
        }
        if (stocksDto.getAskMax() != null){
            stocks.setAskMax(stocksDto.getAskMax());
        }
        stocksRepository.save(stocks);
        dispatchEventToClients();
        stocksHistoricPricesService.atualizarPrices(stocks);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    public SseEmitter subscribe(HttpServletResponse response) {
        response.setHeader("Cache_control", "no-store");
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try {
            emitters.add(sseEmitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion(() -> this.emitters.remove(sseEmitter));

        return sseEmitter;
    }


    public void dispatchEventToClients() {
        for (SseEmitter emitter: emitters){
            try {
                emitter.send(stocksRepository.findAllOrderByUpdate());
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
