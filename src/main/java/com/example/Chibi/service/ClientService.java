package com.example.Chibi.service;

import com.example.Chibi.dto.client.request.ClientRequest;
import com.example.Chibi.dto.client.request.ClientRequestCreate;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.repository.ClientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ClientModel update(ClientRequest clientRequest) {
        ClientModel clientModel = findByEmail(clientRequest.getEmail());
        if (clientModel != null) {
            ClientModel newClientModel = ClientModel.builder()
                    .id(clientModel.getId())
                    .adm(clientModel.getAdm())
                    .nome(clientModel.getNome())
                    .cpf(clientModel.getCpf())
                    .senha(clientModel.getSenha())
                    .carrinho(clientRequest.getCarrinho() != null ? clientRequest.getCarrinho() : null)
                    .endereco(clientRequest.getEndereco() != null ? clientRequest.getEndereco() : null)
                    .cartao(clientRequest.getCartao() != null ? clientRequest.getCartao() : null)
                    .email(clientRequest.getEmail() != null ? clientRequest.getEmail() : null)
                    .dataNascimento(clientModel.getDataNascimento())
                    .build();

            return clientRepository.save(newClientModel);
        }
        return null;
    }

    public void delete(String id) {
        clientRepository.deleteById(new ObjectId(id));
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

}
