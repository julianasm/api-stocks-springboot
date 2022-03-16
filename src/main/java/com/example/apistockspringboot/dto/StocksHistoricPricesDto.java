package com.example.apistockspringboot.dto;


import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.models.StocksHistoricPrices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
public class StocksHistoricPricesDto {

    private Stocks idStock;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Timestamp createdOn;

    public StocksHistoricPricesDto(StocksHistoricPrices stocksHistoricPrices){
        this.idStock = stocksHistoricPrices.getIdStock();
        this.open = stocksHistoricPrices.getOpen();
        this.close = stocksHistoricPrices.getClose();
        this.high = stocksHistoricPrices.getHigh();
        this.low = stocksHistoricPrices.getLow();
        this.createdOn = stocksHistoricPrices.getCreatedOn();
    }


}
