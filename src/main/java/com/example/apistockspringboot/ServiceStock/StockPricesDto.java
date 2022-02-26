package com.example.apistockspringboot.ServiceStock;

import com.example.apistockspringboot.models.Stocks;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockPricesDto {

    private Long id;
    private Double bid_min;
    private Double bid_max;
    private Double ask_min;
    private Double ask_max;

    public StockPricesDto() {
    }

}

