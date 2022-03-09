package com.example.apistockspringboot.service;

import com.example.apistockspringboot.models.Stocks;
import com.example.apistockspringboot.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private final StocksRepository stocksRepository;

    @Autowired
    public StockService(StocksRepository stocksRepository) {
        this.stocksRepository= stocksRepository;
    }

    public Stocks salvar(Stocks stocks) {
        return stocksRepository.save(stocks);
    }


}