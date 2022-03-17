package com.example.apistockspringboot.dto;

import com.example.apistockspringboot.models.Stocks;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
public class StockPricesDto {

    private Long id;
    private String stockSymbol;
    private String stockName;
    private Double bidMin;
    private Double bidMax;
    private Double askMin;
    private Double askMax;
    @UpdateTimestamp
    private Timestamp updatedOn;

    public StockPricesDto() {
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
    }

    public Stocks pegarModel(){
        Stocks stocks = new Stocks();
            stocks.setId(this.id);
            stocks.setStockSymbol(this.stockSymbol);
            stocks.setStockName(this.stockName);
            stocks.setBidMin(this.bidMin);
            stocks.setBidMax(this.bidMax);
            stocks.setAskMin(this.askMin);
            stocks.setAskMax(this.askMax);
        return stocks;
    }

}

