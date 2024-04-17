package com.example.attendance.admin.staff_info_edit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class StaffInfoEditController {
	@RequestMapping("/admin/staff_info_edit/result")
	public String staffInfoEdit(Model model, String name, String tag) {
		System.out.println(name + ":" + tag);
		if (DatabaseConnector.GetInstance().CheckUser(name)){
			DatabaseConnector.GetInstance().ChangeTag(name, tag);
			model.addAttribute("message1", name + "のタグを" + tag + "に変更しました");
			return "admin/StaffInfoEdit";
		}
		
		model.addAttribute("message", "ユーザーが存在しません");
		return "admin/StaffInfoEdit";
	}
}
