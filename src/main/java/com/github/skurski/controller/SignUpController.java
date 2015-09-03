package com.github.skurski.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.User;
import com.github.skurski.services.UserServiceImpl;

@Controller
public class SignUpController {
	
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView getForm(@ModelAttribute User user) {
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView signUp(@Valid @ModelAttribute User user, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("signup");
		}else if(userService.checkIfObjectExistByString("email", user.getEmail())) {
			ModelAndView mav = new ModelAndView("signup");
			mav.addObject("accountExistsMsg", new String("Email exists in our database. Try another email"));
			return mav;
		} else {
			userService.insertRow(user);
			return new ModelAndView("redirect:login");
		}
	}
}