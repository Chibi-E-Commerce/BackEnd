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
}
