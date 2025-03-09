package com.example.Chibi.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PdfService {

    public byte[] pdfDoExtrato(String nome, String cpf, double valor) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Comprovante de Pagamento")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            document.add(new Paragraph(dataAtual).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            document.add(createLabelValue("Valor", String.format("R$ %.2f", valor)).setBold().setFontSize(14));
            document.add(createLabelValue("Para", "CHIBI LOJA OFICIAL"));
            document.add(createLabelValue("De", nome));
            document.add(createLabelValue("CPF", cpf));
            document.add(createLabelValue("ID da transação", gerarHash()));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private Cell createLabelValue(String label, String value) {
        return new Cell().add(new Paragraph(label + ": " + value).setPadding(5).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER);
    }

    private String gerarHash() {
        try {
            String input = "transacao" + System.currentTimeMillis();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString().substring(0, 32);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }
}
