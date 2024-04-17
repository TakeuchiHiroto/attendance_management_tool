package com.example.attendance.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class MainController {

	// main画面を表示
	@GetMapping("/main")
	public String getLogin(Model model) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日(E) HH:mm");
		String formattedDateTime = now.format(formatter);
		model.addAttribute("currentDateTime", formattedDateTime);
		return "main/main";
	}
	
	@GetMapping("/main/log")
	public String getLog() {
		return "main/log";
	}

	@RequestMapping("/main/startwork")
	public String getStartWork(Model model, String name, String password) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日(E) HH:mm");
		String formattedDateTime = now.format(formatter);
		model.addAttribute("currentDateTime", formattedDateTime);
		
		//　ログインできるか検証
		if (DatabaseConnector.GetInstance().isLogin(name, password)) {
			//出勤中であればメッセージ表示
			if (DatabaseConnector.GetInstance().isWorking(name)) {
				model.addAttribute("message", "出勤中です");
				return "main/main";
			}
			//出勤中でなければ出勤開始
			else {
				DatabaseConnector.GetInstance().StartWork(name, password);
			}
		} else {
			model.addAttribute("message", "ユーザーID or パスワードが違います");
			return "main/main";
		}

		return "main/startwork";
	}

	@RequestMapping("/main/endwork")
	public String getEndWork(Model model, String name, String password) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月d日(E) HH:mm");
		String formattedDateTime = now.format(formatter);
		model.addAttribute("currentDateTime", formattedDateTime);
		//　ログインできるか検証
		if (DatabaseConnector.GetInstance().isLogin(name, password)) {
			//出勤中であれば退勤
			if (DatabaseConnector.GetInstance().isWorking(name)) {
				DatabaseConnector.GetInstance().EndWork(name, password);
			}
			//出勤中でなければ退勤できない
			else {
				model.addAttribute("message", "出勤していません");
				return "main/main";
			}
		} else {
			model.addAttribute("message", "ユーザーID or パスワードが違います");
			return "main/main";
		}

		return "main/endwork";
	}

}
