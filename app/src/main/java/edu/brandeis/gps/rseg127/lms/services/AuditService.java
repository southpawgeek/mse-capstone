package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Audit;
import edu.brandeis.gps.rseg127.lms.repos.AuditRepo;

@Service
public class AuditService {

    @Autowired
    private AuditRepo auditRepo;

    public List<Audit> getAllAudits() {
        return auditRepo.findAll();
    }

    public Audit createAudit(Audit audit) {
        return auditRepo.save(audit);
    }

    public Audit getAudit(Integer id) {
        return auditRepo.findById(id).get();
    }
}
