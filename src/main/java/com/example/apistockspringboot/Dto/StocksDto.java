package com.example.apistockspringboot.Dto;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

public class StocksDto {
    private Long id;

    private String stockSymbol;

    private String stockName;

    private Double askMin;

    private Double askMax;

    private Double bidMin;

    private Double bidMax;

    private Timestamp createdOn;

    private Timestamp updatedOn;
}
