package com.example.Chibi.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ExtratoService {

    public String gerarHash(String nome, String cpf, double valor) {
        try {
            String input = nome + cpf + valor;
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

    public byte[] pdfDoExtrato(String nome, String cpf, double valor, String hash) {
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

            document.add(createLabelValue("Valor", String.format("R$ %.2f", valor))
                    .setBold()
                    .setFontSize(16));
            document.add(createLabelValue("Para", "CHIBI LOJA OFICIAL"));
            document.add(createLabelValue("De", nome));
            document.add(createLabelValue("CPF", cpf));
            document.add(createLabelValue("ID da transação", hash));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private Cell createLabelValue(String label, String value) {
        return new Cell().add(new Paragraph(label + ": " + value).setPadding(5).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER);
    }

    public byte[] txtDoExtrato(String nome, String cpf, double valor, String hash) {
        try {
            String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            StringBuilder txtContent = new StringBuilder();

            txtContent.append("Comprovante de Pagamento\n");
            txtContent.append("========================\n");
            txtContent.append("Data: " + dataAtual + "\n\n");
            txtContent.append("Valor: R$ " + String.format("%.2f", valor) + "\n");
            txtContent.append("Para: CHIBI LOJA OFICIAL\n");
            txtContent.append("De: " + nome + "\n");
            txtContent.append("CPF: " + cpf + "\n");
            txtContent.append("ID da transação: " + hash + "\n");

            return txtContent.toString().getBytes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar TXT", e);
        }
    }
}