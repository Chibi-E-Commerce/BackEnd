package com.example.Chibi.controller;

import com.example.Chibi.model.ExtratoFileFormat;
import com.example.Chibi.service.ExtratoService;
import com.example.Chibi.service.order.OrderService;
import org.springframework.http.HttpHeaders;
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
            @RequestParam String pedidoId,
            @RequestParam String formato
    ) {
        try {
            ExtratoFileFormat fileFormat = ExtratoFileFormat.valueOf(formato.toUpperCase().trim());

            byte[] extrato = extratoService.gerarExtrato(pedidoId, fileFormat);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            String.format(
                                    "attachment; filename=%s",
                                    fileFormat.getFileName("comprovante_chibi"))
                    )
                    .contentType(fileFormat.getMediaType())
                    .body(extrato);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
