package edu.brandeis.gps.rseg127.lms.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;

public interface AssetCopyRepo extends JpaRepository<AssetCopy, Integer> {

    @Query("select ac from AssetCopy ac where ac.assetId = ?1")
    List<AssetCopy> findByAssetId(Integer id);

    @Query("select ac from AssetCopy ac where ac.userId = ?1")
    List<AssetCopy> findByUserId(Integer id);
}
