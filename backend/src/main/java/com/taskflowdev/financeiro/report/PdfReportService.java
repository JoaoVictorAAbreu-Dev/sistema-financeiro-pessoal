package com.taskflowdev.financeiro.report;

import com.taskflowdev.financeiro.transaction.FinancialTransaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportService {
    public byte[] createReport(List<FinancialTransaction> transactions) {
        try (PDDocument doc = new PDDocument(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
                cs.newLineAtOffset(50, 730);
                cs.showText("Relatorio Financeiro");
                cs.setFont(PDType1Font.HELVETICA, 10);
                int y = 700;
                for (FinancialTransaction tx : transactions) {
                    cs.newLineAtOffset(0, -18);
                    cs.showText(tx.getType() + " - " + tx.getAmount() + " - " + tx.getDescription());
                    if ((y -= 18) < 80) break;
                }
                cs.endText();
            }
            doc.save(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create PDF report", e);
        }
    }
}
