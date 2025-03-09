package com.example.Chibi.controller;

import com.example.Chibi.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/extrato")
    public ResponseEntity<byte[]> gerarExtrato(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam double valor
    ) {
        try {
            byte[] pdf = pdfService.pdfDoExtrato(nome, cpf, valor);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=comprovante_chibi.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
