package edu.brandeis.gps.rseg127.lms.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.brandeis.gps.rseg127.lms.utils.Message;

@Controller
@RequestMapping("/security")
public class SecurityController {
   private final Logger logger = LoggerFactory.getLogger(SecurityController.class);

   private MessageSource messageSource;

   @RequestMapping("/loginfail")
   public String loginFail(Model uiModel, Locale locale) {
       logger.info("Login failed detected");
       uiModel.addAttribute("message", new Message("error",
               messageSource.getMessage("message_login_fail", new Object[]{}, locale)));
       return "books/list";
   }

   @Autowired
   public void setMessageSource(MessageSource messageSource) {
       this.messageSource = messageSource;
   }
}