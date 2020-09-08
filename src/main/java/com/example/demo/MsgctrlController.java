package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
@Controller
public class MsgctrlController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/msgctrl")
	public String index(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userid = auth.getName();//get logged in username

		List<Message> list = messageService.findAll(userid);
		model.addAttribute("userid", userid );
		model.addAttribute("list",list);
		return "msgctrl";
	}


	@RequestMapping(value="/inputmsg" , method=RequestMethod.POST)
	public String inputmsg(@Validated @ModelAttribute MessageForm form, BindingResult result, Model model) {

		if(result.hasErrors()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String userid = auth.getName();//get logged in username

			List<Message> list = messageService.findAll(userid);
			model.addAttribute("userid", userid );
			model.addAttribute("list",list);
			return "msgctrl";
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String userid = auth.getName();//get logged in username
	    messageService.createMessage(form.getText(), userid);

		return "redirect:index";
	}

}
