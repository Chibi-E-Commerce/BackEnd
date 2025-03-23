package com.example.Chibi.controller;

import com.example.Chibi.service.ExtratoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    private final ExtratoService extratoService;

    public ExtratoController(ExtratoService extratoService) {
        this.extratoService = extratoService;
    }

    @GetMapping("/baixar")
    public ResponseEntity<byte[]> gerarExtrato(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam double valor,
            @RequestParam String formato
    ) {
        try {
            String hash = extratoService.gerarHash(nome, cpf, valor);
            byte[] arquivo;
            if ("pdf".equals(formato)) {
                arquivo = extratoService.pdfDoExtrato(nome, cpf, valor, hash);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprovante_chibi.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(arquivo);
            } else if ("txt".equals(formato)) {
                arquivo = extratoService.txtDoExtrato(nome, cpf, valor, hash);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprovante_chibi.txt")
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(arquivo);
            } else {
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
