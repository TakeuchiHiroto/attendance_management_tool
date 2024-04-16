package com.example.attendance.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	// main画面を表示
	@GetMapping("/main")
	public String getLogin() {
		return "main/main";
	}
	
}

