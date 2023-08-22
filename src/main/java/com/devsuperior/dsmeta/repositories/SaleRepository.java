package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinSumaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query( "SELECT new com.devsuperior.dsmeta.dto.SaleMinReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<SaleMinReportDTO> searchReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query( "SELECT new com.devsuperior.dsmeta.dto.SaleMinSumaryDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller ")
    Page<SaleMinSumaryDTO> searchSumary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
