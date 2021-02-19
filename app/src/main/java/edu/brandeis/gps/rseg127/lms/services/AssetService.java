package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;

@Service
public class AssetService {

    @Autowired
    private AssetRepo assetRepo;

    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    public Asset createAsset(Asset asset) {
        return assetRepo.save(asset);
    }

    public Asset getAsset(Integer id) {
        return assetRepo.getOne(id);
    }
}
