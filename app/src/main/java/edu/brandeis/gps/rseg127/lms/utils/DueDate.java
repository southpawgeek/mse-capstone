// this contains business logic for: 
// - borrow duration
// - in-cart lifetime
package edu.brandeis.gps.rseg127.lms.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DueDate {
    private Timestamp dueDate = Timestamp.valueOf(LocalDateTime.now());
    private String status = "UNKNOWN";

    public DueDate(String status) {
        // force uc status
        this.status = status.toUpperCase();
        updateDueDate();
    }

    private void updateDueDate() {
        // AVAILABLE - set date to now
        if (this.status.equals("AVAILABLE")) {
            dueDate = Timestamp.valueOf(LocalDateTime.now());
        }

        // RESERVED - give the person 1 hour before it's reverted to AVAILABLE
        if (status.equals("RESERVED")) {
            dueDate = Timestamp.valueOf(LocalDateTime.now().plusHours(1));
        }

        // BORROWED - set date to now + 21 days
        if (status.equals("BORROWED")) {
            dueDate = Timestamp.valueOf(LocalDateTime.now().plusDays(21));
        }
    }

    public Timestamp getDueDate() { return dueDate; }
}
