package com.example.apistockspringboot.Dto;


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

    private Stocks id_stock;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Timestamp created_on;

    public StocksHistoricPricesDto(StocksHistoricPrices stocksHistoricPrices){
        this.id_stock = stocksHistoricPrices.getId_stock();
        this.open = stocksHistoricPrices.getOpen();
        this.close = stocksHistoricPrices.getClose();
        this.high = stocksHistoricPrices.getHigh();
        this.low = stocksHistoricPrices.getLow();
        this.created_on = stocksHistoricPrices.getCreated_on();
    }


}
