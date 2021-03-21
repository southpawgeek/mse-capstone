package edu.brandeis.gps.rseg127.lms.configs;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.services.AssetCopyService;

@Configuration
@EnableScheduling
public class CronConfig {
    @Autowired
    AssetCopyService assetCopyService;

    @Scheduled(cron = "59 * * * * *")
    public void markLateCopies() {
        List<AssetCopy> copyList = assetCopyService.getAssetCopyList();
        copyList.forEach(copy -> {
            String status = copy.getStatus();
            Timestamp dueDate = copy.getDueDate();

            if (status.equals("BORROWED")) {
                if (dueDate.before(new Timestamp(System.currentTimeMillis()))) {
                    System.out.println("Mark as LATE: " + copy.getId());
                    copy.setStatus("LATE");
                    assetCopyService.updateAssetCopy(copy);
                }
            }
        });
    }
}
