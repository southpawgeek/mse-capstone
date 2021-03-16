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
        List<Asset> assets = assetRepo.findAll();
        for (Asset asset : assets) {
            CopyCount copyCount = new CopyCount(assetCopyRepo.findByAssetId(asset.getId()));
            asset.setCount(copyCount);
        }
        return assets;
    }

    public Asset createAsset(Asset asset) {
        assetRepo.save(asset);
        AssetCopy assetCopy = new AssetCopy();
        assetCopy.setAssetId(asset.getId());
        assetCopy.setStatus("NEW");
        assetCopyRepo.save(assetCopy);
        return asset;
    }

    public Asset updateAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    public Asset getAsset(Integer id) {
        return assetRepo.findById(id).get();
    }

    public Asset getAssetWithAssetCopyList(Integer id) {
        Asset asset = assetRepo.findById(id).get();
        List<AssetCopy> assetCopyList = assetCopyRepo.findByAssetId(id);
        asset.setCount(new CopyCount(assetCopyList));
        return asset;
    }

    public void deleteAsset(Integer id) {
        assetRepo.deleteById(id);
    }
}
