package com.example.Chibi.model.client;

import com.example.Chibi.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    private ProductModel produto;
    private int quantidade;
}
