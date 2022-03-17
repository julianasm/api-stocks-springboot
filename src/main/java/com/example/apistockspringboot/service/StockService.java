package com.example.apistockspringboot.service;

import com.example.apistockspringboot.dto.GetAllStocksDto;
import com.example.apistockspringboot.dto.StockInfoDto;
import com.example.apistockspringboot.dto.StockPricesDto;
import com.example.apistockspringboot.handleerror.NotFoundException;
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
import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StockService {

    private final StocksRepository stocksRepository;

    private final StocksHistoricPricesService stocksHistoricPricesService;


    public Stocks salvar(Stocks stocks) {
        return stocksRepository.save(stocks);
    }

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    public StockInfoDto getStockInfo(Long id) throws NotFoundException {
        Optional<Stocks> stocks = stocksRepository.findById(id);
        if (stocks.isPresent()){
            return new StockInfoDto(stocks.get());
        } else {
            throw new NotFoundException("STOCK_NOT_FOUND");
        }
    }

    public List<GetAllStocksDto> listByUpdate(){
        return stocksRepository.findAllOrderByUpdate().stream().map(stocks -> new GetAllStocksDto(stocks)).toList();
    }


    public ResponseEntity<StockPricesDto> updateStocks(StockPricesDto stocksDto){
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
        stocksRepository.save(stocksDto.pegarModel());
        dispatchEventToClients();
        stocksHistoricPricesService.atualizarPrices(stocksDto.pegarModel());
        return new ResponseEntity<>(stocksDto, HttpStatus.OK);
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
