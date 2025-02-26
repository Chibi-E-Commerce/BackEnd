package com.example.Chibi.controller;

import com.example.Chibi.dto.client.ClientRequest;
import com.example.Chibi.dto.client.ClientResponse;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/list")
    public List<ClientResponse> getAll() {
        List<ClientResponse> lista = new ArrayList<>();
        for (ClientModel client : clientService.findAll()) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(client, clientResponse);
            lista.add(clientResponse);
        }
        return lista;
    }

    @GetMapping
    public ClientResponse getById(@RequestParam String id) {
        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(clientService.findById(id), clientResponse);
        return clientResponse;
    }

    @PostMapping
    public ClientResponse create(@RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(clientService.insert(clientRequest), clientResponse);
        return clientResponse;
    }

    @PutMapping
    public ClientResponse update(@RequestParam String id, @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(clientService.update(id, clientRequest), clientResponse);
        return clientResponse;
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        clientService.delete(id);
    }
}
