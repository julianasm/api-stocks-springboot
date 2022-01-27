package com.example.apistockspringboot.ServiceStock;

import com.example.apistockspringboot.models.Stocks;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockPricesDto {

    private Long id;
    private Double bid_min_price;
    private Double bid_max_price;
    private Double ask_min_price;
    private Double ask_max_price;

    public StockPricesDto() {
    }

}

