package edu.brandeis.gps.rseg127.lms;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WebController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}