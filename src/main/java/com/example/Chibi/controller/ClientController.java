package com.example.Chibi.controller;

import com.example.Chibi.dto.client.ClientRequest;
import com.example.Chibi.dto.client.ClientResponse;
import com.example.Chibi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/list")
    public List<ClientResponse> getAll() {
        return clientService.findAll().stream().map(ClientResponse::new).collect(Collectors.toList());
    }

    @GetMapping
    public ClientResponse getById(@RequestParam String id) {
        return new ClientResponse(clientService.findById(id));
    }

    @PostMapping
    public ClientResponse create(@RequestBody ClientRequest clientRequest) {
        return new ClientResponse(clientService.insert(clientRequest));
    }

    @PutMapping
    public ClientResponse update(@RequestParam String id, @RequestBody ClientRequest clientRequest) {
        return new ClientResponse(clientService.update(id, clientRequest));
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        clientService.delete(id);
    }
}
