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
    private Double bidMin;
    private Double bidMax;
    private Double askMin;
    private Double askMax;
    @UpdateTimestamp
    private Timestamp updatedOn;

    public StockPricesDto() {
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
    }

}

