package com.example.apistockspringboot.service;

import com.example.apistockspringboot.dto.StocksHistoricPricesDto;
import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.models.StocksHistoricPrices;
import com.example.apistockspringboot.repository.StocksHistoricPricesRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class StocksHistoricPricesService {

    private final StocksHistoricPricesRepository stocksHistoricPricesRepository;

    public void atualizarPrices(Stocks stocks){
        Date date = new Date();
        Optional<StocksHistoricPrices> historicPrices = stocksHistoricPricesRepository.findByIdAndDate(stocks.getId(), new Timestamp(date.getTime()));

        if (historicPrices.isPresent()){
            if((historicPrices.get().getHigh() < stocks.getAskMin())) {
                historicPrices.get().setHigh(stocks.getAskMin());
            }
            if (historicPrices.get().getLow() > stocks.getAskMin()) {
                historicPrices.get().setLow(stocks.getAskMin());
            }
            stocksHistoricPricesRepository.save(historicPrices.get());
        }
        else {
            stocksHistoricPricesRepository.save(new StocksHistoricPrices(stocks));
        }

    }

    public List<StocksHistoricPricesDto> historicPrices(Long idStock){
        return stocksHistoricPricesRepository.findByIdStock(idStock).stream().map(StocksHistoricPricesDto::new).toList();
    }
}
