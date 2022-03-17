package com.example.apistockspringboot.dto;

import com.example.apistockspringboot.models.Stocks;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetAllStocksDto {
    private Long id;

    private String stockSymbol;

    private String stockName;

    private Double askMin;

    private Double askMax;

    private Double bidMin;

    private Double bidMax;


    public GetAllStocksDto(Stocks stocks){
        this.id = stocks.getId();
        this.stockSymbol = stocks.getStockSymbol();
        this.stockName = stocks.getStockName();
        this.askMin = stocks.getAskMin();
        this.askMax = stocks.getAskMax();
        this.bidMin = stocks.getBidMin();
        this.bidMax = stocks.getBidMax();
    }
}
