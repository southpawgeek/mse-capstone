package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.entities.CopyCount;
import edu.brandeis.gps.rseg127.lms.repos.AssetCopyRepo;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;

@Service
public class AssetService {
    @Autowired
    private AssetRepo assetRepo;

    @Autowired
    private AssetCopyRepo assetCopyRepo;

    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    public Asset createAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    public Asset updateAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    public Asset getAsset(Integer id) {
        return assetRepo.findById(id).get();
    }

    public Asset getAssetWithCopies(Integer id) {
        Asset myAsset = assetRepo.findById(id).get();
        List<AssetCopy> copies = assetCopyRepo.findByAssetId(id);
        myAsset.setCount(new CopyCount(copies));
        return myAsset;
    }

    public void deleteAsset(Integer id) {
        assetRepo.deleteById(id);
    }
}
