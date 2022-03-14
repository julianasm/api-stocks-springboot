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
    private Stocks idStock;

    private Double open = 0.0;

    private Double close = 0.0;

    private Double high = 0.0;

    private Double low = 0.0;

    @Column(name="created_on")
    private Timestamp createdOn;

    public StocksHistoricPrices(Stocks stocks) {
        Date date = new Date();
        this.idStock = stocks;
        this.open = stocks.getAskMin();
        this.close = stocks.getAskMin();
        this.high = stocks.getAskMin();
        this.low  = stocks.getAskMin();
        this.createdOn = new Timestamp(date.getTime());
    }

}
