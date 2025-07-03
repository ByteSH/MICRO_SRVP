package com.connect.product_service.entry;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product
{

    @Id
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal price;

}
