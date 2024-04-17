package com.example.attendance.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.database.DatabaseConnector;


@Controller
public class AttendingWork {
	
	
	
	@RequestMapping("/main/working")
	public String AttendingWoker_screen(Model model) {
		Map<String, String> tagOptions = new HashMap<>();
        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
		for (String i : tags) {
			tagOptions.put(i, i);
		}
        model.addAttribute("tagOptions", tagOptions);
		
	
	    return "/main/working";
	}
	
	@RequestMapping("/main/working/result")
	public String AttendantWorkingResult(Model model, String tag) {
		ArrayList<String> t = DatabaseConnector.GetInstance().GetUserNameList(tag);
		ArrayList<String> names = new ArrayList<>();
		for(String i : t) {
			if(DatabaseConnector.GetInstance().isWorking(i)) {
				names.add(i);
			}
		}
        model.addAttribute("users", names);
		return "/main/workingResult";
	}
	
}
