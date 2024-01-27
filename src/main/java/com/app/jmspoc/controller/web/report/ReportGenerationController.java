package com.app.jmspoc.controller.web.report;

import com.app.jmspoc.service.business.ReportGenerationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/generate/report")
public class ReportGenerationController {

    private final ReportGenerationService reportGenerationService;

    public ReportGenerationController(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @GetMapping
    public String generateAndSendReport() {
        reportGenerationService.initReportGeneration();
        return "redirect:/admin/dashboard";
    }
}