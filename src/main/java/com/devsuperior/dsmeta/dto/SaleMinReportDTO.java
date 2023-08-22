package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleMinReportDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SaleMinReportDTO(Long id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    public Double getAmount() {
        return amount;
    }
    public String getName() {
        return name;
    }
}
