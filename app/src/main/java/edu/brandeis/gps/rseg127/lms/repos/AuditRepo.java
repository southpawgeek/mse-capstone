package edu.brandeis.gps.rseg127.lms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.brandeis.gps.rseg127.lms.entities.Audit;

public interface AuditRepo extends JpaRepository<Audit, Integer> {

}
