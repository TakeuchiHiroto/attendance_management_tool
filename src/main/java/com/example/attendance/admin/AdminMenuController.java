package com.example.attendance.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMenuController {
	
	@GetMapping("/admin")
	public String AdminMenu() {
		return "admin/AdminMenu";
	}
	
	@GetMapping("/admin/staff_info_entry")
	public String StaffInfoEntry() {
		return "admin/StaffInfoEntry";
	}
	@GetMapping("/admin/staff_info_edit")
	public String StaffInfoEdit() {
		return "admin/StaffInfoEdit";
	}
	@GetMapping("/admin/staff_info_delete")
	public String StaffInfoDelete() {
		return "admin/StaffInfoDelete";
	}
	@GetMapping("/admin/log_edit")
	public String LogEdit() {
		return "admin/LogEdit";
	}
	@GetMapping("/admin/tag_edit")
	public String TagEdit() {
		return "admin/TagEdit";
	}
}
