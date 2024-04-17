package com.example.attendance.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class AdminMenuController {
	
	@RequestMapping("/admin")
	public String AdminMenu() {
		return "admin/AdminMenu";
	}
	
	@RequestMapping("/admin/staff_info_entry")
	public String StaffInfoEntry() {
		return "admin/StaffInfoEntry";
	}
	@RequestMapping("/admin/staff_info_edit")
	public String StaffInfoEdit(Model model,String username, String password) {
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/StaffInfoEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/staff_info_delete")
	public String StaffInfoDelete(Model model,String username, String password) {
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/StaffInfoDelete";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/log_edit")
	public String LogEdit(Model model,String username, String password) {
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/LogEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/tag_edit")
	public String TagEdit(Model model,String username, String password) {
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/TagEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
}
