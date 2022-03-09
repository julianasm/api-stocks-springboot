package com.example.apistockspringboot.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks_historic_prices")
public class StocksHistoricPrices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_stock")
    private Stocks id_stock;

    private Double open = 0.0;

    private Double close = 0.0;

    private Double high = 0.0;

    private Double low = 0.0;

    private Timestamp created_on;

    public StocksHistoricPrices(Stocks stocks) {
        Date date = new Date();
        this.id_stock = stocks;
        this.open = stocks.getAsk_min();
        this.close = stocks.getAsk_min();
        this.high = stocks.getAsk_min();
        this.low  = stocks.getAsk_min();
        this.created_on = new Timestamp(date.getTime());
    }

}
