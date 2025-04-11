package com.example.Chibi.service.client;

import com.example.Chibi.dto.client.request.ClientRequest;
import com.example.Chibi.dto.client.request.ClientRequestCreate;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.model.ProductModel;
import com.example.Chibi.repository.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientModel findById(String id) {
        return clientRepository.findById(new ObjectId(id)).orElse(null);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public List<ClientModel> sortAll() {
        List<ClientModel> clientModels = clientRepository.findAll();
        Collections.sort(clientModels);
        return clientModels;
    }
    public List<ClientModel> search(List<Predicate<ClientModel>> filters) {
        List<ClientModel> clients = findAll();
        List<ClientModel> clientsFiltered = new ArrayList<>();
        for (Predicate<ClientModel> filter : filters) {
            clientsFiltered = clients.stream().filter(filter).toList();
            if (!clientsFiltered.isEmpty()) {
                return clientsFiltered;
            }
        }
        return clientsFiltered;

    }

    public ClientModel update(String id, ClientRequest clientRequest) {
        ClientModel clientModel = findById(id);
        if (clientModel != null) {
            ClientModel newClientModel = ClientModel.builder()
                    .id(clientModel.getId())
                    .adm(clientRequest.getAdm())
                    .nome(clientRequest.getNome().isEmpty() ? clientModel.getNome() : clientRequest.getNome())
                    .cpf(clientModel.getCpf())
                    .senha(clientModel.getSenha())
                    .carrinho(clientRequest.getCarrinho() != null ? clientRequest.getCarrinho() : clientModel.getCarrinho() != null && !clientModel.getCarrinho().isEmpty() ? clientModel.getCarrinho() : null)
                    .endereco(clientRequest.getEndereco() != null ? clientRequest.getEndereco() : null)
                    .cartao(clientRequest.getCartao() != null && !clientRequest.getCartao().isEmpty() ? clientRequest.getCartao() : clientModel.getCartao() != null ? clientModel.getCartao() : null)
                    .email(!clientRequest.getEmail().isEmpty() ? clientRequest.getEmail() : clientModel.getEmail())
                    .dataNascimento(clientRequest.getDataNascimento() == null ? clientModel.getDataNascimento() : clientRequest.getDataNascimento() )
                    .build();

            return clientRepository.save(newClientModel);
        }
        return null;
    }

    public boolean delete(String email) {
        ClientModel clientModel = findByEmail(email);
        if (clientModel != null) {
            clientRepository.delete(clientModel);
            return true;
        }
        return false;
    }

    public ClientModel findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    public boolean isUserValid(String email, String senha) {
        ClientModel clientModel = findByEmail(email);
        if (clientModel == null) {
            return false;
        }
        return clientModel.getSenha().equals(senha);
    }

    public ClientModel createUser(ClientRequestCreate clientRequest) {
        if (clientRepository.findByCpf(clientRequest.getCpf()) != null ||
                clientRepository.findByEmail(clientRequest.getEmail()) != null) {
            throw new RuntimeException("Usuário já cadastrado!");
        }

        ClientModel clientModel = new ClientModel();
        clientModel.setAdm(clientRequest.getAdm());
        clientModel.setNome(clientRequest.getNome());
        clientModel.setEmail(clientRequest.getEmail());
        clientModel.setSenha(clientRequest.getSenha());
        clientModel.setCpf(clientRequest.getCpf());
        clientModel.setDataNascimento(clientRequest.getDataNascimento());

        return clientRepository.save(clientModel);
    }

    public boolean updatePassword(String email, String novaSenha) {
        ClientModel cliente = clientRepository.findByEmail(email);
        if (cliente != null) {
            cliente.setSenha(novaSenha);
            clientRepository.save(cliente);
            return true;
        }
        return false;
    }

}
