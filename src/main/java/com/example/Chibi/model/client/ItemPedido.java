package com.example.Chibi.model.client;

import com.example.Chibi.dto.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedido {

    private ProductRequest produto;
    private int quantidade;
}
