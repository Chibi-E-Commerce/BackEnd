package com.example.Chibi.dto.search;

import com.example.Chibi.dto.OrderDto;
import com.example.Chibi.model.OrderModel;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.service.order.OrderFilter;
import com.example.Chibi.service.product.ProductFilter;
import com.example.Chibi.service.util.FilterBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.function.Predicate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSearchDto implements Search<OrderModel> {
    ObjectId idCliente;
    Double valorMinimo;
    Double valorMaximo;
    Integer itensMinimo;
    Integer itensMaximo;
    ProductSearchDto productSearch;

    @Override
    public List<Predicate<OrderModel>> breakdown() {
        FilterBuilder<OrderModel> filterBuilder = new FilterBuilder<>();

        filterBuilder.add(OrderFilter.porIdCliente(idCliente), idCliente != null);
        filterBuilder.add(OrderFilter.valorMinimo(valorMinimo), valorMinimo != 0);
        filterBuilder.add(OrderFilter.valorMaximo(valorMaximo), valorMaximo != 0);
        filterBuilder.add(OrderFilter.quantidadeItensMinima(itensMinimo), valorMinimo != 0);
        filterBuilder.add(OrderFilter.quantidadeItensMaxima(itensMaximo), valorMaximo != 0);
        filterBuilder.add(OrderFilter.porProduto(productSearch), productSearch != null);

        return filterBuilder.unwrap();
    }
}
