package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.repos.AssetCopyRepo;

@Service
public class AssetCopyService {

    @Autowired
    private AssetCopyRepo assetCopyRepo;

    public AssetCopy getAssetCopy(Integer id) {
        return assetCopyRepo.findById(id).get();
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
}
