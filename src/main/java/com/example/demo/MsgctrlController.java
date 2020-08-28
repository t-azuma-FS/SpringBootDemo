package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
@Controller
public class MsgctrlController {

	@RequestMapping("/msgctrl")
	public String index(Model model) {
		List<String> list = new ArrayList<String>(Arrays.asList("aa","bb","cc"));
		model.addAttribute("test","test messge" );
		model.addAttribute("list",list);
		return "msgctrl";
	}
}
