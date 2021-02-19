package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.brandeis.gps.rseg127.lms.entities.Audit;
import edu.brandeis.gps.rseg127.lms.services.AuditService;

@Controller
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/audit-log")
    public String getAllAudit(Model model) {
        List<Audit> audits = auditService.getAllAudits();
        model.addAttribute("audits", audits);
        return "audit-log";
    }
}
