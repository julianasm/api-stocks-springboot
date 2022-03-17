package com.example.apistockspringboot.service;

import antlr.build.Tool;
import com.example.apistockspringboot.dto.GetAllStocksDto;
import com.example.apistockspringboot.dto.StockInfoDto;
import com.example.apistockspringboot.dto.StockPricesDto;
import com.example.apistockspringboot.handleerror.NotFoundException;
import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.repository.StocksRepository;
import lombok.RequiredArgsConstructor;
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

    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(StockService.class);

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
        return stocksRepository.findAllOrderByUpdate().stream().map(GetAllStocksDto::new).toList();
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
            logger.error(e.getMessage());
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
