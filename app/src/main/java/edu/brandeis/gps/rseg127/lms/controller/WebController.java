package edu.brandeis.gps.rseg127.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "Hello from WebController...");
		return "index";
	}
}