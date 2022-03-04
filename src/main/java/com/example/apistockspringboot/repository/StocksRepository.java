package com.example.apistockspringboot.repository;

import com.example.apistockspringboot.models.Stocks;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;


public interface StocksRepository extends JpaRepository<Stocks, Long> {



}
