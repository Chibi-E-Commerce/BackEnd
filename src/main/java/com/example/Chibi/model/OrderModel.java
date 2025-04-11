package com.example.Chibi.model;

import com.example.Chibi.dto.product.ProductResponse;
import com.example.Chibi.model.client.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pedido")
public class OrderModel {
    @Id
    private ObjectId id;
    private Double total;
    private LocalDate data;
    private ClientModel client;
    private List<ItemPedido> itens;

    public List<ProductModel> getProductList() {
        return this.itens.stream().map(x -> {
            ProductResponse dto = x.getProduto();

            ProductModel p = new ProductModel();
            p.setMarca(dto.getMarca());
            p.setDescricao(dto.getDescricao());
            p.setPreco(dto.getPreco());
            p.setCategoria(dto.getCategoria());
            p.setUrlImagem(dto.getUrlImagem());
            p.setNome(dto.getNome());

            return p;
        }).toList();
    }
}
