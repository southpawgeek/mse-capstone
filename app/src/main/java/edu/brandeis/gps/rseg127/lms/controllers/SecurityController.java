//package edu.brandeis.gps.rseg127.lms.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import edu.brandeis.gps.rseg127.lms.entities.Asset;
//import edu.brandeis.gps.rseg127.lms.entities.Audit;
//import edu.brandeis.gps.rseg127.lms.entities.User;
//import edu.brandeis.gps.rseg127.lms.services.AssetService;
//import edu.brandeis.gps.rseg127.lms.services.AuditService;
//import edu.brandeis.gps.rseg127.lms.services.UserService;

//@Controller
//@RequestMapping("/security")
//public class SecurityController {
//    private final Logger logger = LoggerFactory.getLogger(SecurityController.class);
//
//    private MessageSource messageSource;
//
//    @RequestMapping("/loginfail")
//    public String loginFail(Model uiModel, Locale locale) {
//        logger.info("Login failed detected");
//        uiModel.addAttribute("message", new Message("error",
//                messageSource.getMessage("message_login_fail", new Object[]{}, locale)));
//        return "books/list";
//    }
//
//    @Autowired
//    public void setMessageSource(MessageSource messageSource) {
//        this.messageSource = messageSource;
//    }
//}
//}