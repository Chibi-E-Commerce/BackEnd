package com.example.Chibi.model.client;

import com.example.Chibi.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedido {

    private ProductResponse produto;
    private int quantidade;
}
