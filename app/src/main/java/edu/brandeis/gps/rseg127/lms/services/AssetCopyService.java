package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.repos.AssetCopyRepo;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;

@Service
public class AssetCopyService {

    @Autowired
    private AssetCopyRepo assetCopyRepo;

    @Autowired
    private AssetRepo assetRepo;

    public AssetCopy getAssetCopy(Integer id) {
        AssetCopy myCopy = assetCopyRepo.findById(id).get();
        myCopy.setAsset(assetRepo.findById(myCopy.getAssetId()).get());
        return myCopy;
    }

    public List<AssetCopy> getAllAssetCopies() {
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
}
