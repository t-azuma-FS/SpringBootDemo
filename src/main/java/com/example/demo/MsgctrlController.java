package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
@Controller
public class MsgctrlController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/msgctrl")
	public String index(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userid = auth.getName();//get logged in username

	    // create new message without user input
	    messageService.createMessage("test message", userid);

		List<Message> list = messageService.findAll(userid);
		model.addAttribute("userid", userid );
		model.addAttribute("list",list);
		return "msgctrl";
	}
}
