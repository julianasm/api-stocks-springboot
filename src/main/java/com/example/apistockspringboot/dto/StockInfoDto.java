package com.example.apistockspringboot.dto;

import com.example.apistockspringboot.models.Stocks;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockInfoDto {

    private Long id;
    private String stockSymbol;
    private String stockName;

    public StockInfoDto(Stocks stocks){
        this.id = stocks.getId();
        this.stockSymbol = stocks.getStockSymbol();
        this.stockName = stocks.getStockName();
    }
}
