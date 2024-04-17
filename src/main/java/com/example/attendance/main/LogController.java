package com.example.attendance.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogController
{
	// main画面を表示
	@PostMapping("/main/log")
	public String ViewLog()
	{
		return "main/log";
	}
}