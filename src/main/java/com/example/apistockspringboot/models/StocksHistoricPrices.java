package com.example.apistockspringboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class StocksHistoricPrices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_stock")
    private Long id_stock;

    private Double open;

    private Double close;

    private Double high;

    private Double low;

    private Timestamp created_on;

    public StocksHistoricPrices(Stocks stocks) {
        Date date = new Date();
        this.open = stocks.getAsk_max();
        this.close = stocks.getAsk_min();
        this.high = stocks.getAsk_min();
        this.low  = stocks.getAsk_min();
        this.created_on = new Timestamp(date.getTime());
    }

}
