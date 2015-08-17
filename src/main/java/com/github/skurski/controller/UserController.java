package com.github.skurski.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.User;
import com.github.skurski.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("add-user")
	public ModelAndView getForm(@ModelAttribute User user) {
		return new ModelAndView("form");
	}
	
	@RequestMapping("register")
	public ModelAndView registerUser(@ModelAttribute User user) {
		userService.insertRow(user);
		return new ModelAndView("redirect:users-list");
	}
	
	@RequestMapping("users-list")
	public ModelAndView getList() {
		List userList = userService.getList();
		return new ModelAndView("list","userList",userList);
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		userService.deleteRow(id);
		return new ModelAndView("redirect:users-list");
	}
	
	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,@ModelAttribute User user) {
		User userObj = userService.getRowById(id);
		return new ModelAndView("edit","userObj",userObj);
	}
	
	@RequestMapping("update")
	public ModelAndView updateUser(@ModelAttribute User user) {
		userService.updateRow(user);
		return new ModelAndView("redirect:users-list");
	}

}
