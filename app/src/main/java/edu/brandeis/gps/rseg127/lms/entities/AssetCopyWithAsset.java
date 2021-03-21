package edu.brandeis.gps.rseg127.lms.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssetCopyWithAsset {
    public Asset asset = new Asset();
    public AssetCopy assetCopy = new AssetCopy();
    public Boolean isLate = false;
}
