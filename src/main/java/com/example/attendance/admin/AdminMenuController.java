package com.example.attendance.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, String> nameOptions = new HashMap<>();
		ArrayList<String> name = DatabaseConnector.GetInstance().GetNameList();
		for (String i : name) {
			nameOptions.put(i, i);
		}
	    model.addAttribute("nameOptions", nameOptions);
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
		        Map<String, String> tagOptions = new HashMap<>();
		        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
				for (String i : tags) {
					tagOptions.put(i, i);
				}
		        model.addAttribute("tagOptions", tagOptions);
				return "admin/StaffInfoEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/staff_info_delete")
	public String StaffInfoDelete(Model model,String username, String password) {
		Map<String, String> nameOptions = new HashMap<>();
		ArrayList<String> name = DatabaseConnector.GetInstance().GetNameList();
		for (String i : name) {
			nameOptions.put(i, i);
		}
	    model.addAttribute("nameOptions", nameOptions);
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/StaffInfoDelete";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/log_edit")
	public String LogEdit(Model model,String username, String password, String search_name) {
		Map<String, String> nameOptions = new HashMap<>();
		ArrayList<String> name = DatabaseConnector.GetInstance().GetNameList();
		for (String i : name) {
			nameOptions.put(i, i);
		}
	    model.addAttribute("nameOptions", nameOptions);
		if (search_name == null) {
			if(!DatabaseConnector.GetInstance().isLogin(username,password)){
				if(!DatabaseConnector.GetInstance().isAdmin(username,password)) {
					model.addAttribute("message", "管理者ではありません。");
					return "admin/AdminMenu";
				}
			}
			model.addAttribute("message", "ユーザー名を入力してください");
			return "admin/LogEdit";
		}
		if (DatabaseConnector.GetInstance().CheckUser(search_name)) {
			Map<String, String> dateOptions = new HashMap<>();
			ArrayList<String> date = DatabaseConnector.GetInstance().GetDate(search_name);
			for (String i : date) {
				dateOptions.put(i, i);
			}
	        model.addAttribute("dateOptions", dateOptions);
			return "admin/LogEdit";
		}
		
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/LogEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/tag_add")
	public String TagAdd(Model model,String username, String password) {
		System.out.println("aaaa");
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				return "admin/TagAdd";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
	@RequestMapping("/admin/tag_edit")
	public String TagEdit(Model model,String username, String password) {
		if(DatabaseConnector.GetInstance().isLogin(username,password)){
			if(DatabaseConnector.GetInstance().isAdmin(username,password)) {
				Map<String, String> tagOptions = new HashMap<>();
		        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
				for (String i : tags) {
					tagOptions.put(i, i);
				}
		        model.addAttribute("tagOptions", tagOptions);
				return "admin/TagEdit";
			}
		}
		model.addAttribute("message", "管理者ではありません。");
		return "admin/AdminMenu";
	}
}
