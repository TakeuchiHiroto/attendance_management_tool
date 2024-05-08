package com.example.attendance.admin.staff_info_edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.admin.AdminMenuController;
import com.example.attendance.database.DatabaseConnector;

@Controller
public class StaffInfoEditController {
	@RequestMapping("/admin/staff_info_edit/result")
	public String staffInfoEdit(Model model, String name, String tag) {
		if (AdminMenuController.CheckTime()) {
			model.addAttribute("message", "セッションが切れました。再度ログインしてください。");
			return "admin/AdminMenu";
		}	
		else {
			AdminMenuController.SetTime();
		}
		Map<String, String> nameOptions = new HashMap<>();
		ArrayList<String> all_name = DatabaseConnector.GetInstance().GetNameList();
		for (String i : all_name) {
			nameOptions.put(i, i);
			System.out.println(i);
		}
		
        Map<String, String> tagOptions = new HashMap<>();
        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
		for (String i : tags) {
			tagOptions.put(i, i);
		}
        model.addAttribute("tagOptions", tagOptions);
        
		model.addAttribute("nameOptions", nameOptions);
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
