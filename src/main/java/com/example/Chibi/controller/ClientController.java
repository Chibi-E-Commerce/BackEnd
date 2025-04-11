package com.example.Chibi.controller;

import com.example.Chibi.dto.client.request.ClientRequest;
import com.example.Chibi.dto.client.ClientResponse;
import com.example.Chibi.dto.client.request.ClientRequestCreate;
import com.example.Chibi.dto.search.ClientSearchDto;
import com.example.Chibi.model.ClientModel;
import com.example.Chibi.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;
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

    @GetMapping("/get-user")
    public ResponseEntity<ClientResponse> getByEmail(@RequestParam String email) {
        ClientModel cm = clientService.findByEmail(email);
        if (cm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientResponse(cm));

    }

    @PostMapping("/search")
    public List<ClientResponse> search(@RequestBody ClientSearchDto clientSearchDto) {
        List<Predicate<ClientModel>> predicates = clientSearchDto.breakdown();
        List<ClientModel> clientModels = clientService.search(predicates);

        return clientModels.stream().map(ClientResponse::new).toList();
    }

    @GetMapping("/sort")
    public List<ClientResponse> getClientSorted() {
        return clientService.sortAll().stream().map(ClientResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody ClientRequestCreate clientRequest) {
        ClientModel novoUsuario = clientService.createUser(clientRequest);
        return ResponseEntity.ok(new ClientResponse(novoUsuario));
    }

    @PutMapping
    public ClientResponse update(@RequestParam String id,@RequestBody ClientRequest clientRequest) {
        return new ClientResponse(clientService.update(id, clientRequest));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam String email) {
        return clientService.delete(email) ? ResponseEntity.status(200).body(true) : ResponseEntity.status(404).body(false);
    }
}
