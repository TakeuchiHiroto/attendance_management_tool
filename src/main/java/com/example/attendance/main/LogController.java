package com.example.attendance.main;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class LogController
{
	// main画面を表示
	@PostMapping("/main/log")
	public String ViewLog(@RequestParam("name") String str1, @RequestParam("password") String str2, Model model)
	{
		ArrayList<String> list = DatabaseConnector.GetInstance().CheckLog(str1, str2);
		
		// ユーザー名、パスワードをModelに登録
		model.addAttribute("list", list);
		
		// log.htmlに遷移
		return "main/log";
	}
}