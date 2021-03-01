package edu.brandeis.gps.rseg127.lms.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.brandeis.gps.rseg127.lms.utils.HttpCodeMessage;


@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("notification", new HttpCodeMessage(status));
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
