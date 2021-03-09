package edu.brandeis.gps.rseg127.lms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.brandeis.gps.rseg127.lms.utils.Message;


@Controller
public class WebSecurityController {
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,
            Model model) {
        if (error != null) {
            Message notification = new Message("bad", "Login failed!");
            model.addAttribute("notification", notification);
        }

        if (logout != null) {
            Message notification = new Message("default", "Successfully logged out.");
            model.addAttribute("notification", notification);
        }
        return "login";
    }
}
