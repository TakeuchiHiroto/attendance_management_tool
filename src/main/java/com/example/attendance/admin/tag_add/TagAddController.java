package com.example.attendance.admin.tag_add;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.attendance.admin.AdminMenuController;
import com.example.attendance.database.DatabaseConnector;

@Controller
public class TagAddController {
	@GetMapping("/admin/tag_add/result")
	public String TagAddResult(Model model, String tagname) {
		if (AdminMenuController.CheckTime()) {
			model.addAttribute("message", "セッションが切れました。再度ログインしてください。");
			return "admin/AdminMenu";
		}	
		else {
			AdminMenuController.SetTime();
		}
		if(DatabaseConnector.GetInstance().CheckTag(tagname)) {
			DatabaseConnector.GetInstance().AddTag(tagname);
			model.addAttribute("message", "タグを追加しました");
			return "admin/TagAdd";
		}
		model.addAttribute("message", "タグ名が重複しています");
		return "admin/TagAdd";
	}

}
