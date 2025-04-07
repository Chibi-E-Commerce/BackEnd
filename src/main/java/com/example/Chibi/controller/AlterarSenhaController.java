package com.example.Chibi.controller;

import com.example.Chibi.model.ClientModel;
import com.example.Chibi.service.AlterarSenhaService;
import com.example.Chibi.service.EmailService;
import com.example.Chibi.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AlterarSenhaController {

    @Autowired
    private AlterarSenhaService alterarSenhaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/esqueci-senha")
    public ResponseEntity<?> solicitarCodigo(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        ClientModel cliente = clientService.findByEmail(email);
        if (cliente != null) {
            String codigo = String.format("%06d", new Random().nextInt(1000000));
            alterarSenhaService.gerarCodigo(email, codigo);
            emailService.sendResetCode(email, codigo);
        }
        return ResponseEntity.ok("Se o e-mail estiver cadastrado, um código foi enviado!");
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<?> validarCodigo(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String codigo = request.get("codigo");

        if (alterarSenhaService.validarCodigo(email, codigo)) {
            return ResponseEntity.ok("Código válido.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido ou expirado!");
        }
    }

    @PostMapping("/alterar-senha")
    public ResponseEntity<?> alterarSenha(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String novaSenha = request.get("novaSenha");

        boolean sucesso = clientService.updatePassword(email, novaSenha);
        if (sucesso) {
            return ResponseEntity.ok("Senha alterada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }
}
