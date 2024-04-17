package com.example.attendance.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public String ViewLog(@RequestParam("name") String name, @RequestParam("password") String password, Model model)
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日(E) HH:mm");
		String formattedDateTime = now.format(formatter);
		model.addAttribute("currentDateTime", formattedDateTime);
		
		//　ログインできるか検証
		if (!DatabaseConnector.GetInstance().isLogin(name, password))
		{
			model.addAttribute("message", "ユーザーID or パスワードが違います");
			return "main/main";
		}
		
		ArrayList<String> list = DatabaseConnector.GetInstance().CheckLog(name, password);
		
		// ユーザー名、パスワードをModelに登録
		model.addAttribute("list", list);
		
		// log.htmlに遷移
		return "main/log";
	}
}