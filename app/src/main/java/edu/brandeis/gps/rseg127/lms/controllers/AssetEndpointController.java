// REST endpoints for Asset and AssetCopy
package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.services.AssetCopyService;
import edu.brandeis.gps.rseg127.lms.services.AssetService;

@RestController
public class AssetEndpointController {
    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetCopyService assetCopyService;

    // Asset
    @PostMapping(consumes="application/json", produces="application/json", path="/api/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        return new ResponseEntity<>(assetService.createAsset(asset), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/api/assets/{id}")
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset asset, @PathVariable Integer id) {
        // pull up a copy of the current asset
        Asset updated_asset = assetService.getAsset(id);

        // fill in the blanks with submitted data
        updated_asset.setTitle(asset.getTitle());
        updated_asset.setIsbn(asset.getIsbn());
        updated_asset.setCallNumber(asset.getCallNumber());

        return new ResponseEntity<>(assetService.updateAsset(updated_asset), HttpStatus.CREATED);
    }

    @GetMapping(path="/api/assets/{id}", produces="application/json")
    public ResponseEntity<Asset> getAsset(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(assetService.getAssetWithAssetCopyList(id), HttpStatus.OK);
    }

    @GetMapping(path="/api/assets", produces="application/json")
    public ResponseEntity<List<Asset>> getAllAssets() {
        return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @DeleteMapping(path="/api/assets/{id}", produces="application/json")
    public ResponseEntity<String> deleteAsset(@PathVariable(value = "id") Integer id) {
        assetService.deleteAsset(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // AssetCopy
    @PostMapping(consumes="application/json", produces="application/json", path="/api/assets/copy")
    public ResponseEntity<AssetCopy> createAssetCopy(@RequestBody AssetCopy assetCopy) {
        return new ResponseEntity<>(assetCopyService.createAssetCopy(assetCopy), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(consumes = "application/json", produces = "application/json", path = "/api/assets/copy/{id}")
    public ResponseEntity<AssetCopy> updateAssetCopy(@RequestBody AssetCopy assetCopy, @PathVariable Integer id) {
        Integer assetId = assetCopyService.getAssetCopy(id).getAssetId();
        assetCopy.setId(id);
        assetCopy.setAssetId(assetId);

        return new ResponseEntity<>(assetCopyService.updateAssetCopy(assetCopy), HttpStatus.CREATED);
    }

    @GetMapping(path="/api/assets/copy/{id}", produces="application/json")
    public ResponseEntity<AssetCopy> getAssetCopy(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(assetCopyService.getAssetCopy(id), HttpStatus.OK);
    }

    @GetMapping(path="/api/assets/copy", produces="application/json")
    public ResponseEntity<List<AssetCopy>> getAssetCopyList() {
        return new ResponseEntity<>(assetCopyService.getAssetCopyList(), HttpStatus.OK);
    }

    @DeleteMapping(path="/api/assets/copy/{id}", produces="application/json")
    public ResponseEntity<String> deleteAssetCopy(@PathVariable(value = "id") Integer id) {
        assetCopyService.deleteAssetCopy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
