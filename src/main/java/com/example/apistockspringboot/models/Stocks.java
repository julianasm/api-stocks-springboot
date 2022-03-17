package com.example.apistockspringboot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name="stocks")
public class Stocks implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="stock_symbol")
    private String stockSymbol;

    @Column(name="stock_name")
    private String stockName;

    @Column(name="ask_min")
    private Double askMin;

    @Column(name="ask_max")
    private Double askMax;

    @Column(name="bid_min")
    private Double bidMin;

    @Column(name="bid_max")
    private Double bidMax;

    @CreationTimestamp
    @Column(name="created_on")
    private Timestamp createdOn;

    @UpdateTimestamp
    @Column(name="updated_on")
    private Timestamp updatedOn;

    public Stocks() {
        this.createdOn = Timestamp.valueOf(LocalDateTime.now());
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
    }

    public Stocks(Long id, Double bidMin, Double bidMax) {
        this.id = id;
        this.bidMin = bidMin;
        this.bidMax = bidMax;
    }

    public Stocks(Long id, String stockSymbol, String stockName, Double bidMin, Double bidMax, Double askMin, Double askMax) {
    }
}
