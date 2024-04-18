package com.example.attendance.admin.log_edit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class LogEditController {
	@RequestMapping("/admin/log_edit/result")
	public String LogEditResult(Model model, String date, String ChangeDate) {
		DatabaseConnector.GetInstance().ChangeDate(date, ChangeDate);
		model.addAttribute("message1", date + "の日付を" + ChangeDate + "に変更しました");
		return "admin/LogEdit";
	}
	
}
