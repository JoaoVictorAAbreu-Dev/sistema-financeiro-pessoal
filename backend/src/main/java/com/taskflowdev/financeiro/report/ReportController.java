package com.taskflowdev.financeiro.report;

import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final FinancialTransactionService service;
    private final PdfReportService pdfReportService;
    public ReportController(FinancialTransactionService service, PdfReportService pdfReportService) { this.service = service; this.pdfReportService = pdfReportService; }
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> pdf(Authentication auth) {
        byte[] bytes = pdfReportService.createReport(service.listByEmail(auth.getName()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-financeiro.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
