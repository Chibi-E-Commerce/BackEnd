package com.example.Chibi.controller;

import com.example.Chibi.dto.client.ClientRequest;
import com.example.Chibi.dto.client.ClientResponse;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(@RequestBody ClientRequest clientRequest) {
        try {
            ClientModel novoUsuario = clientService.createUser(clientRequest);
            return ResponseEntity.ok(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
