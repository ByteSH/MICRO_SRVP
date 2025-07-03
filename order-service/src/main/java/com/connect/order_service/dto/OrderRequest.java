package com.connect.order_service.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderRequest {

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
