package edu.brandeis.gps.rseg127.lms.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.brandeis.gps.rseg127.lms.entities.CopyAssetUserView;

public interface CopyAssetUserViewRepo extends JpaRepository<CopyAssetUserView, Integer> {
    @Query("select v from CopyAssetUserView v where v.status = ?1")
    List<CopyAssetUserView> findByStatus(String status);

    @Query("select v from CopyAssetUserView v where v.username = ?1")
    List<CopyAssetUserView> findByUsername(String username);

    @Query("select v from CopyAssetUserView v where v.assetId = ?1")
    List<CopyAssetUserView> findByAssetId(Integer id);
}
