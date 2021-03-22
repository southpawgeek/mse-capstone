package edu.brandeis.gps.rseg127.lms.configs;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.entities.Cart;
import edu.brandeis.gps.rseg127.lms.services.AssetCopyService;
import edu.brandeis.gps.rseg127.lms.services.CartService;

@Configuration
@EnableScheduling
public class CronConfig {
    @Autowired
    AssetCopyService assetCopyService;

    @Autowired
    CartService cartService;

    // every hour, mark items as late
    @Scheduled(cron = "59 59 * * * *")
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

    // every minute, revert status if asset is in bookbag too long
    // also, delete the cart entry
    @Scheduled(cron = "13 * * * * *")
    public void returnReservedCopies() {
        List<AssetCopy> copyList = assetCopyService.getAssetCopyList();
        copyList.forEach(copy -> {
            Integer id = copy.getId();
            String status = copy.getStatus();
            Timestamp dueDate = copy.getDueDate();

            if (status.equals("RESERVED")) {
                if (dueDate.before(new Timestamp(System.currentTimeMillis()))) {
                    System.out.println("Return to AVAILABLE: " + id);
                    copy.setStatus("AVAILABLE");
                    copy.setUserId(null);
                    copy.setDueDate(Timestamp.valueOf(LocalDateTime.now()));
                    assetCopyService.updateAssetCopy(copy);

                    Cart item = cartService.getByCopyId(id);
                    cartService.deleteCartItem(item.getId());
                    
                }
            }
        });
    }
}
