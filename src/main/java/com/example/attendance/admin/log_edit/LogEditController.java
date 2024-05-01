package com.example.attendance.admin.log_edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.admin.AdminMenuController;
import com.example.attendance.database.DatabaseConnector;

@Controller
public class LogEditController {
	@RequestMapping("/admin/log_edit/result")
	public String LogEditResult(Model model, String date, String ChangeDate) {
		if (AdminMenuController.CheckTime()) {
			model.addAttribute("message", "セッションが切れました。再度ログインしてください。");
			return "admin/AdminMenu";
		}
		else {
			AdminMenuController.SetTime();
		}
		DatabaseConnector.GetInstance().ChangeDate(date, ChangeDate);
		model.addAttribute("message1", date + "の日付を" + ChangeDate + "に変更しました");
		Map<String, String> nameOptions = new HashMap<>();
        ArrayList<String> name = DatabaseConnector.GetInstance().GetNameList();
        for (String i : name) {
			nameOptions.put(i, i);
			System.out.println(i);
		}
        model.addAttribute("nameOptions", nameOptions);
		return "admin/LogEdit";
	}
	
}
