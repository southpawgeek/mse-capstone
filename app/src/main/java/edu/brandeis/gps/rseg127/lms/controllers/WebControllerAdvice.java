// somewhat adapted from https://stackoverflow.com/questions/51623853/is-there-a-mustache-equivalent-to-thymeleafs-secauthorize-tag
package edu.brandeis.gps.rseg127.lms.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class WebControllerAdvice {
    @ModelAttribute
    public void pullAuthData(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username != "anonymousUser") {
            model.addAttribute("auth-principal", auth.getPrincipal());
            model.addAttribute("auth-initial", username.charAt(0));
            model.addAttribute("auth-username", auth.getName());
        }
    }
}
