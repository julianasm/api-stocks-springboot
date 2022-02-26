package com.example.apistockspringboot.repository;

import com.example.apistockspringboot.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StocksRepository extends JpaRepository<Stocks, Long> {


}
