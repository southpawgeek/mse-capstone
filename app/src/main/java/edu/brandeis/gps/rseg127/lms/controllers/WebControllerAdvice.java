// somewhat adapted from https://stackoverflow.com/questions/51623853/is-there-a-mustache-equivalent-to-thymeleafs-secauthorize-tag
package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.brandeis.gps.rseg127.lms.entities.*;
import edu.brandeis.gps.rseg127.lms.repos.*;
import edu.brandeis.gps.rseg127.lms.services.*;

@ControllerAdvice
public class WebControllerAdvice {
    private Integer loggedInUserId = 0;
    private String loggedInUser = "";

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartService cartService;

    @Autowired
    AssetCopyService assetCopyService;

    @ModelAttribute
    public void pullAuthData(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username != "anonymousUser") {
            this.loggedInUser = auth.getName();
            User user = userRepo.findByUsername(this.loggedInUser);
            loggedInUserId = user.getId();
            model.addAttribute("auth-principal", auth.getPrincipal());
            model.addAttribute("auth-initial", username.charAt(0));
            model.addAttribute("auth-username", loggedInUser);
            model.addAttribute("auth-userid", loggedInUserId);
            model.addAttribute("auth-LIB",user.getUserType().equals("LIB") || user.getUserType().equals("ADM"));

            // pull cart data for authenticated user
            List<Cart> cartList = new ArrayList<Cart>();
            cartList = cartService.getUserCart(loggedInUserId);

            Integer cartSize = cartList.size();
            Boolean hasCart = false;
            if (cartSize != 0) {
                hasCart = true;
            }
            model.addAttribute("user-cart-exists", hasCart);
            model.addAttribute("user-cart-count", cartSize);
            model.addAttribute("user-cart", cartList);

            // pull loan data for authenticated user
            List<AssetCopyWithAsset> copyList = userService.getUserLoans(this.loggedInUserId);

            Integer loanSize = copyList.size();
            Boolean hasLoan = false;
            if (loanSize != 0) {
                hasLoan = true;
            }
            model.addAttribute("user-loan-exists", hasLoan);
            model.addAttribute("user-loan-count", loanSize);
            model.addAttribute("user-loan", copyList);
        }
    }
}
