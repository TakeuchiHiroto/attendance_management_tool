package com.example.attendance.admin.staff_info_delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class StaffInfoDeleteController {
@GetMapping("/admin/staff_info_delete/result")
public String StaffInfoDeleteResult(Model model, String username) {
	if(DatabaseConnector.GetInstance().CheckUser(username)) {
		DatabaseConnector.GetInstance().DeleteUser(username);
		model.addAttribute("message", "削除できました");
		return "admin/StaffInfoDelete";
	}
	model.addAttribute("message", "ユーザー名がありません");
	return "admin/StaffInfoDelete";
}
}
