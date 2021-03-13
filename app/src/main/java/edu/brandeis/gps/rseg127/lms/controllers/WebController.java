package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.brandeis.gps.rseg127.lms.entities.Asset;
import edu.brandeis.gps.rseg127.lms.entities.Audit;
import edu.brandeis.gps.rseg127.lms.entities.User;
import edu.brandeis.gps.rseg127.lms.services.AssetService;
import edu.brandeis.gps.rseg127.lms.services.AuditService;
import edu.brandeis.gps.rseg127.lms.services.UserService;

@Controller
public class WebController {
	@RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome! There isn't much here yet.");
        return "index";
    }

	@Autowired
    private AssetService assetService;
    @GetMapping("/assets")
    public String getAllAssets(Model model) {
        List<Asset> assets = assetService.getAllAssets();
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
        // NYI
        return "borrowed";
    }

    @GetMapping("/bookbag")
    public String getBookBag(Model model) {
        // NYI
        return "bookbag";
    }

	@Autowired
    private UserService userService;
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @Autowired
    private AuditService auditService;
    @GetMapping("/audit-log")
    public String getAllAudit(Model model) {
        List<Audit> audits = auditService.getAllAudits();
        model.addAttribute("audits", audits);
        return "audit-log";
    }
}
