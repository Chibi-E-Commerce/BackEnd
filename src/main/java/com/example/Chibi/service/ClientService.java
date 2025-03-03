package com.example.Chibi.service;

import com.example.Chibi.dto.client.ClientRequest;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientModel insert(ClientRequest clientRequest) {
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRequest, clientModel);
        return clientRepository.save(clientModel);
    }

    public ClientModel findById(String id) {
        return clientRepository.findById(new ObjectId(id)).orElse(null);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public ClientModel update(String id, ClientRequest clientRequest) {
        ClientModel clientModel = findById(id);
        if (clientModel != null) {
            BeanUtils.copyProperties(clientRequest, clientModel);
            return clientRepository.save(clientModel);
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

    public boolean userExists(String cpf, String nome, String email) {
        ClientModel clientByCpf = clientRepository.findByCpf(cpf);
        ClientModel clientByEmail = clientRepository.findByEmail(email);
        ClientModel clientByNome = clientRepository.findByNome(nome);
        return clientByCpf != null || clientByEmail != null || clientByNome != null;
    }


    public ClientModel createUser(ClientRequest clientRequest) {
        ClientModel clientModel = new ClientModel();
        clientModel.setCpf(clientRequest.getCpf());
        clientModel.setNome(clientRequest.getNome());
        clientModel.setEmail(clientRequest.getEmail());
        clientModel.setSenha(clientRequest.getSenha());
        clientModel.setIdade(clientRequest.getIdade());

        return clientRepository.save(clientModel);
    }
}
