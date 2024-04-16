package com.example.attendance.admin.staff_info_entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class StaffInfoEntryController {
	@GetMapping("/admin/staff_info_entry/result")
	public String StaffInfoEntryResult(String name, String pass) {
		DatabaseConnector.GetInstance().RegisterUser(name, pass);
		return "admin/StaffInfoEntryResult";
	}
	
}
