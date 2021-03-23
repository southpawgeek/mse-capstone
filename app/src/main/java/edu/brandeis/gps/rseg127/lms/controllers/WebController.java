package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.Audit;
import edu.brandeis.gps.rseg127.lms.entities.CopyAssetUserView;
import edu.brandeis.gps.rseg127.lms.entities.User;
import edu.brandeis.gps.rseg127.lms.repos.CopyAssetUserViewRepo;
import edu.brandeis.gps.rseg127.lms.services.AssetService;
import edu.brandeis.gps.rseg127.lms.services.AuditService;
import edu.brandeis.gps.rseg127.lms.services.UserService;

@Controller
public class WebController {
	@Autowired
    private AssetService assetService;

	@Autowired
    private UserService userService;

    @Autowired
    private AuditService auditService;

    @Autowired
    private CopyAssetUserViewRepo cauRepo;

	@RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome! There isn't much here yet.");
        return "index";
    }

    @GetMapping("/assets")
    public String getAllAssets(Model model) {
        List<Asset> assets = assetService.getAllAssetsWithCopies();
        model.addAttribute("assets", assets);
        model.addAttribute("assets-page", true);
        return "assets";
    }

    @GetMapping("/browse")
    public String getAllAssetsBrowse(Model model) {
        List<Asset> assets = assetService.getAllAssets();
        model.addAttribute("assets", assets);
        model.addAttribute("browse-page", true);
        return "assets";
    }

    @GetMapping("/borrowed")
    public String getBorrowedList(Model model) {
        return "borrowed";
    }

    @GetMapping("/bookbag")
    public String getBookBag(Model model) {
        return "bookbag";
    }

    @GetMapping("/loans")
    public String getLoans(Model model) {
        List<CopyAssetUserView> borrowedItems = cauRepo.findByStatus("BORROWED");
        List<CopyAssetUserView> lateItems = cauRepo.findByStatus("LATE");

        Integer borrowedTotal = borrowedItems.size();
        Integer lateTotal = lateItems.size();
        Integer loanTotal = borrowedTotal + lateTotal;
        Boolean existsBorrowed = false;
        Boolean existsLate = false; 

        if (borrowedTotal > 0) {
            existsBorrowed = true;
        }

        if (lateTotal > 0) {
            existsLate = true;
        }

        model.addAttribute("current-exists", existsBorrowed);
        model.addAttribute("late-exists", existsLate);
        model.addAttribute("total-items", loanTotal);
        model.addAttribute("total-current-items", borrowedTotal);
        model.addAttribute("total-late-items", lateTotal);
        model.addAttribute("borrowed-items", borrowedItems);
        model.addAttribute("late-items", lateItems);
        return "loans";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/audit-log")
    public String getAllAudit(Model model) {
        List<Audit> audits = auditService.getAllAudits();
        model.addAttribute("audits", audits);
        return "audit-log";
    }
}
