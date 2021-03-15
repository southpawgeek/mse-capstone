// this is for taking inventory of an asset's copies
package edu.brandeis.gps.rseg127.lms.entities;

import java.util.Hashtable;
import java.util.List;
import lombok.Getter;

@Getter
public class CopyCount {
    private List<AssetCopy> copies;
    private Hashtable<String, Integer> stats = new Hashtable<String, Integer>();

    public CopyCount(List<AssetCopy> copies) {
        this.copies = copies;
        processCopies();
    }

    private void processCopies () {
        stats.put("total", copies.size());


        for (AssetCopy copy : copies) {
            String status = copy.getStatus().toLowerCase();

            if (stats.containsKey(status)) {
                stats.put(status, stats.get(status) + 1);
            } else {
                stats.put(status, 1);
            }
        }
    }
}