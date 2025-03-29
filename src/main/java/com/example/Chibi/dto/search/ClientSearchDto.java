package com.example.Chibi.dto.search;

import com.example.Chibi.model.ClientModel;
import com.example.Chibi.service.client.ClientFilter;
import com.example.Chibi.service.util.FilterBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSearchDto implements Search<ClientModel> {
    private String nome;
    private String email;
    private String cpf;

    @Override
    public List<Predicate<ClientModel>> breakdown() {
        FilterBuilder<ClientModel> filterBuilder = new FilterBuilder<>();
        filterBuilder.add(ClientFilter.nome(nome), !nome.isBlank());
        filterBuilder.add(ClientFilter.email(email), !email.isBlank());
        filterBuilder.add(ClientFilter.cpf(cpf), !cpf.isBlank());
        return filterBuilder.unwrap();
    }
}
