package com.example.apistockspringboot.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockInfoDto {

    private Long id;
    private String stock_symbol;
    private String stock_name;

    public StockInfoDto() {
    }
}
