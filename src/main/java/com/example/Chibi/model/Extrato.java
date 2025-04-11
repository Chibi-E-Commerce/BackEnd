package com.example.Chibi.model;

import com.example.Chibi.dto.product.ProductResponse;
import com.example.Chibi.model.client.ItemPedido;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public record Extrato(
    OrderModel pedido,
    ExtratoFileFormat format
) {

    public byte[] gerar() throws Exception {

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

    private byte[] geraPdf() throws RuntimeException, IOException {
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

            Table productTable = new Table(new float[]{3, 1, 2}); // Column widths
            productTable.setWidth(500); // Set total width in points

            document.add(new Paragraph("Itens")
                    .setBold()
                    .setFontSize(14));

            productTable.addHeaderCell(new Cell().add(new Paragraph("Nome")));
            productTable.addHeaderCell(new Cell().add(new Paragraph("Quantidade")));
            productTable.addHeaderCell(new Cell().add(new Paragraph("Preço")));


            for (ItemPedido item : pedido.getItens()) {
                ProductResponse dto = item.getProduto();

                productTable.addCell(new Cell().add(new Paragraph(dto.getNome())));
                productTable.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantidade()))));
                productTable.addCell(new Cell().add(new Paragraph(String.format("R$ %.2f", dto.getPreco()))));
            }

            document.add(productTable);

            document.add(createLabelValue("Total de itens", String.valueOf(pedido.getItens().size())));
            document.add(createLabelValue("Total pago", String.format("R$ %.2f", pedido.getTotal()))
                    .setBold()
                    .setFontSize(12));
            document.add(createLabelValue("ID da transação", String.valueOf(pedido.getId())));

            document.close();
            return out.toByteArray();
        }
    }

    private byte[] geraTxt() {
        String dataAtual = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        ClientModel client = pedido.getClient();

        StringBuilder doc = new StringBuilder();

        doc.append("Comprovante de Pagamento\n");
        doc.append("====================================================\n");
        doc.append("Data: ").append(dataAtual).append("\n\n");
        doc.append("Valor: R$ ").append(String.format("%.2f", pedido.getTotal())).append("\n");
        doc.append("Para: CHIBI LOJA OFICIAL\n");
        doc.append("De: ").append(client.getNome()).append("\n");
        doc.append("CPF: ").append(client.getCpf()).append("\n");
        doc.append("\n");
        doc.append("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n");
        doc.append("|                  Itens da compra                 |\n");
        doc.append("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n");

        doc.append("Número | ").append(String.format("%-24s", "Nome do Produto")).append(" | ").append(String.format("%-3s", "Qnt")).append(" | ").append(String.format("%-13s", "Preço")).append("\n");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido itemPedido = pedido.getItens().get(i);
            ProductResponse dto = itemPedido.getProduto();
            doc.append(String.format("#%5d | %-24s | %-3d | %-13s%n", i + 1, dto.getNome(), itemPedido.getQuantidade(), String.format("R$ %.2f", dto.getPreco())));
        }
        doc.append("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n");
        doc.append("Total de itens: ").append(pedido.getItens().size()).append("\n");

        doc.append("\n\n");
        doc.append("Total: R$ ").append(String.format("%.2f", pedido.getTotal())).append("\n");

        doc.append("ID da transação: ").append(pedido.getId()).append("\n");
        doc.append("====================================================\n");

        String finalText = doc.toString();

        return finalText.getBytes();
    }
}
