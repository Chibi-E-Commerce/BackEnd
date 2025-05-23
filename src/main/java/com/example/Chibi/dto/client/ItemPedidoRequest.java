package com.example.Chibi.dto.client;

import com.example.Chibi.dto.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoRequest {
    ProductRequest product;
    Integer quantidade;
}
