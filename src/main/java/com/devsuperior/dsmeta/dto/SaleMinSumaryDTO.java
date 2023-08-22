package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleMinSumaryDTO {

    private String sellerName;
    private Double total;

    public SaleMinSumaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
