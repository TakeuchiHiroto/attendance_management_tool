package com.example.attendance.admin.staff_info_entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.attendance.database.DatabaseConnector;


@Controller
public class StaffInfoEntryController {
	@GetMapping("/admin/staff_info_entry/result")
	public String StaffInfoEntryResult(Model model, String name, String password) {
		if(DatabaseConnector.GetInstance().CheckRegisterUser(name, password)) {
			DatabaseConnector.GetInstance().RegisterUser(name, password);
			model.addAttribute("message", name + "さんを登録しました");
			return "admin/StaffInfoEntry";
		}
		model.addAttribute("message", "ユーザー名が重複しています");
		return "admin/StaffInfoEntry";
	}
	
}
