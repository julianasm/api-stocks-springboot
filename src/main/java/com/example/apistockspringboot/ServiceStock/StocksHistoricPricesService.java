package com.example.apistockspringboot.ServiceStock;

import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.models.StocksHistoricPrices;
import com.example.apistockspringboot.repository.StocksHistoricPricesRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
            if((historicPrices.get().getHigh() < stocks.getAsk_min())) {
                historicPrices.get().setHigh(stocks.getAsk_min());
            }
            if (historicPrices.get().getLow() > stocks.getAsk_min()) {
                historicPrices.get().setLow(stocks.getAsk_min());
            }
            stocksHistoricPricesRepository.save(historicPrices.get());
        }
        else {
            stocksHistoricPricesRepository.save(new StocksHistoricPrices(stocks));
        }

    }
}
