package edu.brandeis.gps.rseg127.lms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.AssetCopy;
import edu.brandeis.gps.rseg127.lms.entities.Cart;
import edu.brandeis.gps.rseg127.lms.repos.AssetCopyRepo;
import edu.brandeis.gps.rseg127.lms.repos.AssetRepo;
import edu.brandeis.gps.rseg127.lms.repos.CartRepo;
import edu.brandeis.gps.rseg127.lms.utils.DueDate;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    AssetCopyRepo assetCopyRepo;

    @Autowired
    AssetRepo assetRepo;

    public List<Cart> getUserCart(Integer id) {
        List<Cart> cartList = cartRepo.findByUserId(id);

        cartList.forEach(item -> {
            // making this explicit for clarity
            Integer copyId = item.getCopyId();
            AssetCopy copy = assetCopyRepo.findById(copyId).orElse(new AssetCopy());
            // get asset using copy id
            Integer assetId = copy.getAssetId();
            Asset asset = assetRepo.findById(assetId).orElse(new Asset());
            // set cart asset
            item.setAsset(asset);
        });

        return cartList;
    }

    public List<Cart> getAllCartItems() {
        return cartRepo.findAll();
    }

    public void deleteCartItem(Integer id) {
        cartRepo.deleteById(id);
    }

    // adding to bookbag automatically reserves the asset copy
    public Cart createCartItem(Cart item) {
        AssetCopy copy = assetCopyRepo.findById(item.getCopyId()).orElse(new AssetCopy());
        copy.setUserId(item.getUserId());
        copy.setStatus("RESERVED");

        DueDate dueDate = new DueDate("RESERVED");
        copy.setDueDate(dueDate.getDueDate());
        assetCopyRepo.save(copy);
        return cartRepo.save(item);
    }

    public Cart getByCopyId(Integer id) {
        return cartRepo.findByCopyId(id);
    }
}
