package edu.brandeis.gps.rseg127.lms.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssetCopyListWithAsset {
    public Asset asset = new Asset();
    public List<AssetCopy> assetCopyList = new ArrayList<AssetCopy>();
}
