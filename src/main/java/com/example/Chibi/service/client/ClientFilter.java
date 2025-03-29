package com.example.Chibi.service.client;

import com.example.Chibi.model.ClientModel;

import java.util.function.Predicate;

public class ClientFilter {


    public static Predicate<ClientModel> nome(final String nome) {
        return clientModel -> clientModel.getNome() != null && clientModel.getNome().toLowerCase().trim().contains(nome.toLowerCase().trim());
    }

    public static Predicate<ClientModel> email(final String email) {
        return clientModel -> clientModel.getEmail().toLowerCase().trim().contains(email.toLowerCase().trim());
    }

    public static Predicate<ClientModel> cpf(final String cpf) {
        return clientModel -> clientModel.getCpf().replaceAll("[.,-]", "").toLowerCase().trim().contains(cpf.replaceAll("[.,-]", "").toLowerCase().trim());
    }
}
