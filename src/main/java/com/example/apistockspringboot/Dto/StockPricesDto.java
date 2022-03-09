package com.example.apistockspringboot.Dto;

import com.example.apistockspringboot.models.Stocks;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class StockPricesDto {

    private Long id;
    private Double bid_min;
    private Double bid_max;
    private Double ask_min;
    private Double ask_max;
    @UpdateTimestamp
    private Timestamp updated_on;

    public StockPricesDto() {
        this.updated_on = Timestamp.valueOf(LocalDateTime.now());
    }

}

