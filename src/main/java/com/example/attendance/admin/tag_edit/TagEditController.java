package com.example.attendance.admin.tag_edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.attendance.database.DatabaseConnector;

@Controller
public class TagEditController {
	@GetMapping("/admin/tag_edit/result")
	public String TagEditResult(Model model, String tag, String newtagname) {
		if(DatabaseConnector.GetInstance().CheckTag(newtagname)) {
			DatabaseConnector.GetInstance().TagEdit(tag , newtagname);
			model.addAttribute("message", "タグ名を変更しました");
			
			Map<String, String> tagOptions = new HashMap<>();
	        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
			for (String i : tags) {
				tagOptions.put(i, i);
			}
	        model.addAttribute("tagOptions", tagOptions);
	        
			return "admin/TagEdit";
		}
		model.addAttribute("message", "タグ名が重複しています");
		
		Map<String, String> tagOptions = new HashMap<>();
        ArrayList<String> tags = DatabaseConnector.GetInstance().GetTagList();
		for (String i : tags) {
			tagOptions.put(i, i);
		}
        model.addAttribute("tagOptions", tagOptions);
        
		return "admin/TagEdit";		
	}

}
