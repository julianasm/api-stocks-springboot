package com.example.apistockspringboot.repository;

import com.example.apistockspringboot.ServiceStock.StocksHistoricPricesDto;
import com.example.apistockspringboot.models.StocksHistoricPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface StocksHistoricPricesRepository extends JpaRepository<StocksHistoricPrices, Long> {
    @Query( nativeQuery = true, value = "select * from stocks_historic_prices as shp where shp.id_stock = :id_stock and date_trunc('hour', shp.created_on) = date_trunc('hour', cast (:now as Timestamp)))")
    Optional<StocksHistoricPrices> findByIdAndDate(@Param("id_stock") Long id_stock, @Param("now") Timestamp agora);

    @Query(nativeQuery = true, value = "SELECT * from stocks_historic_prices where id_stock = :id_stock")
    List<StocksHistoricPrices> findByIdStock(@Param("id_stock") Long id_stock);
}
