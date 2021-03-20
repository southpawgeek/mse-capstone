package edu.brandeis.gps.rseg127.lms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.brandeis.gps.rseg127.lms.entities.AssetCopyWithAsset;
import edu.brandeis.gps.rseg127.lms.entities.User;
import edu.brandeis.gps.rseg127.lms.repos.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AssetCopyService assetCopyService;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Integer id) {
        return userRepo.findById(id).get();
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    public List<AssetCopyWithAsset> getUserLoans(Integer id) {
        List<AssetCopyWithAsset> copies = assetCopyService.getByUserIdWithAsset(id);
        List<AssetCopyWithAsset> filteredCopies = new ArrayList<AssetCopyWithAsset>();

        for (AssetCopyWithAsset copy : copies) {
            String status = copy.getAssetCopy().getStatus();
            if (status.equals("BORROWED") || status.equals("LATE")) {
                filteredCopies.add(copy);
            }
        }
        return filteredCopies;
    }
}
