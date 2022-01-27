package com.example.apistockspringboot.repository;

import com.example.apistockspringboot.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StocksRepository extends JpaRepository<Stocks, Long> {


   /*@Modifying
    @Query(value = "UPDATE stocks as s set s.bid_min = CASE WHEN (s.bid_min >= :min_price) THEN :min_price when (s.bid_min is null) then :min_price END WHERE s.id = :id", nativeQuery = true)
    Integer findByIdStockSetBidMin(@Param("min_price") Double min_price,
                          @Param("max_price") Double max_price,
                          @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE stocks as s set s.bid_max = CASE WHEN (s.bid_max <= :max_price) THEN :max_price when (bid_max is null) then :max_price END WHERE id = :id", nativeQuery = true)
    Integer findByIdStockSetBidMax(@Param("max_price") Double max_price,
                                @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE stocks set ask_min = CASE WHEN (ask_min >= :min_price) THEN :min_price when (ask_min is null) then :min_price END WHERE id = :id", nativeQuery = true)
    Integer findByIdStockSetAskMin(@Param("min_price") Double min_price,
                                   @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE stocks set ask_max = CASE WHEN (ask_max <= :max_price) THEN :max_price when (ask_max is null) then :max_price END WHERE id = :id", nativeQuery = true)
    Integer findByIdStockSetAskMax(@Param("max_price") Double max_price,
                                   @Param("id") Long id);
    */
}
