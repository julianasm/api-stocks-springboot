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

    private String stock_symbol;

    private String stock_name;

    private Double ask_min;

    private Double ask_max;

    private Double bid_min;

    private Double bid_max;

    @CreationTimestamp
    private Timestamp created_on;

    @UpdateTimestamp
    private Timestamp updated_on;

    public Stocks() {
        this.created_on = Timestamp.valueOf(LocalDateTime.now());
        this.updated_on = Timestamp.valueOf(LocalDateTime.now());
    }

    public Stocks(Long id, Double bid_min, Double bid_max) {
        this.id = id;
        this.bid_min = bid_min;
        this.bid_max = bid_max;
    }

}
