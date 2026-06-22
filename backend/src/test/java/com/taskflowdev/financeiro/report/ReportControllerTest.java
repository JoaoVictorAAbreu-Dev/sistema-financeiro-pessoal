package com.taskflowdev.financeiro.report;

import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportControllerTest {
    @Test
    void pdfReturnsExpectedHeadersAndBytes() {
        byte[] pdfBytes = "%PDF-1.4".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        FinancialTransactionService transactionService = new FinancialTransactionService(null, null) {
            @Override
            public java.util.List<com.taskflowdev.financeiro.transaction.FinancialTransaction> listByEmail(String email) {
                return List.of();
            }
        };
        PdfReportService pdfReportService = new PdfReportService() {
            @Override
            public byte[] createReport(java.util.List<com.taskflowdev.financeiro.transaction.FinancialTransaction> transactions) {
                return pdfBytes;
            }
        };
        ReportController controller = new ReportController(transactionService, pdfReportService);

        var response = controller.pdf(authentication("user@example.com"));

        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
        assertEquals("attachment; filename=relatorio-financeiro.pdf", response.getHeaders().getFirst("Content-Disposition"));
        assertArrayEquals(pdfBytes, response.getBody());
    }

    private Authentication authentication(String email) {
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(email, null, List.of());
    }
}
