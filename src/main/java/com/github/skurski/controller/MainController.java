package com.github.skurski.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.User;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView getForm() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/info")
	public ModelAndView infoUser(@ModelAttribute User user) {
		return new ModelAndView("info");
	}
	
}
