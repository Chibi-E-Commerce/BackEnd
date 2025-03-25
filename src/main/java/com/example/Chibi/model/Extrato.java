package com.example.Chibi.model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;

public record Extrato(
    OrderModel pedido,
    ExtratoFileFormat format
) {

    public byte[] gerar() throws RuntimeException {

        switch (format) {
            case PDF -> {
                return geraPdf();
            }
            case TXT -> {
                return geraTxt();
            }
            default -> throw new IllegalArgumentException("Formato de extrato inválido");
        }
    }

    private Cell createLabelValue(String label, String value) {
        return new Cell().add(new Paragraph(label + ": " + value).setPadding(5).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER);
    }

    private byte[] geraPdf() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            ClientModel cliente = pedido.getClient();

            document.add(new Paragraph("Comprovante de Pagamento")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            document.add(new Paragraph(dataAtual).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            document.add(createLabelValue("Valor", String.format("R$ %.2f", pedido.getTotal()))
                    .setBold()
                    .setFontSize(16));

            document.add(createLabelValue("Para", "CHIBI LOJA OFICIAL"));
            document.add(createLabelValue("De", cliente.getNome()));
            document.add(createLabelValue("CPF", cliente.getCpf()));
            document.add(createLabelValue("ID da transação", String.valueOf(pedido.getId())));

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private byte[] geraTxt() {
        try {
            String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            ClientModel client = pedido.getClient();

            String txtContent = "Comprovante de Pagamento\n" +
                    "========================\n" +
                    "Data: " + dataAtual + "\n\n" +
                    "Valor: R$ " + String.format("%.2f", pedido.getTotal()) + "\n" +
                    "Para: CHIBI LOJA OFICIAL\n" +
                    "De: " + client.getNome() + "\n" +
                    "CPF: " + client.getCpf() + "\n" +
                    "ID da transação: " + pedido.getId() + "\n";

            return txtContent.getBytes();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar TXT", e);
        }
    }
}
