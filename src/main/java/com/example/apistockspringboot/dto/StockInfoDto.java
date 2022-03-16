package com.example.apistockspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockInfoDto {

    private Long id;
    private String stockSymbol;
    private String stockName;

}
