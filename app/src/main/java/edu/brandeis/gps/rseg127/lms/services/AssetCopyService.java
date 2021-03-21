package edu.brandeis.gps.rseg127.lms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopyListWithAsset;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopyWithAsset;
import edu.brandeis.gps.rseg127.lms.repos.AssetCopyRepo;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;

@Service
public class AssetCopyService {

    @Autowired
    private AssetCopyRepo assetCopyRepo;

    @Autowired
    private AssetRepo assetRepo;

    public AssetCopy getAssetCopy(Integer id) {
        return assetCopyRepo.findById(id).get();
    }

    public AssetCopyListWithAsset getAssetCopyListWithAsset(Integer id) {
        AssetCopyListWithAsset copyList = new AssetCopyListWithAsset();
        copyList.setAssetCopyList(getByAssetId(id));
        copyList.setAsset(assetRepo.findById(id).get());
        return copyList;
    }

    public List<AssetCopy> getAssetCopyList() {
        return assetCopyRepo.findAll();
    }

    public AssetCopy createAssetCopy(AssetCopy assetCopy) {
        return assetCopyRepo.save(assetCopy);
    }

    public AssetCopy updateAssetCopy(AssetCopy updatedAssetCopy) {
        return assetCopyRepo.save(updatedAssetCopy);
    }

    public void deleteAssetCopy(Integer id) {
        assetCopyRepo.deleteById(id);
    }

    public List<AssetCopy> getByAssetId(Integer id) {
        return assetCopyRepo.findByAssetId(id);
    }

    public List<AssetCopy> getByUserId(Integer id) {
        List<AssetCopy> copies = assetCopyRepo.findByUserId(id);

        return copies;
    }

    public List<AssetCopyWithAsset> getByUserIdWithAsset(Integer id) {
        List<AssetCopy> copies = assetCopyRepo.findByUserId(id);
        List<AssetCopyWithAsset> copiesWithAsset = new ArrayList<AssetCopyWithAsset>();

        for (AssetCopy copy : copies) {
            AssetCopyWithAsset copyWA = new AssetCopyWithAsset();
            copyWA.setAssetCopy(copy);
            copyWA.setAsset(assetRepo.findById(copy.getAssetId()).orElse(new Asset()));
            copiesWithAsset.add(copyWA);
        }
        return copiesWithAsset;
    }
}
