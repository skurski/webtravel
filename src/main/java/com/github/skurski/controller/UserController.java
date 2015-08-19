package com.github.skurski.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.User;
import com.github.skurski.services.UserService;

@Controller
@SessionAttributes({"travelObj", "userId"})
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public ModelAndView getForm() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public ModelAndView getForm(@ModelAttribute User user) {
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView signUp(@Valid @ModelAttribute User user, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("signup");
		}else if(userService.checkIfUserExistsByEmail(user.getEmail())) {
			ModelAndView mav = new ModelAndView("signup");
			mav.addObject("accountExistsMsg", new String("Email exists in our database. Try another email"));
			return mav;
		} else {
			userService.insertRow(user);
			return new ModelAndView("redirect:login");
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView getLogin(@ModelAttribute User user) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView authUser(@ModelAttribute User user) {
		User validUser = userService.getUserByEmail(user.getEmail());
		if(validUser != null) {
			if(user.getPassword().equals(validUser.getPassword())) {
				ModelAndView mav = new ModelAndView("redirect:info");
				mav.addObject("userId", user);
				return mav;			
			} else {
				ModelAndView mav = new ModelAndView("login");
				mav.addObject("passwordNotMatchMsg", new String("Password don't match to email address. Try again"));
				return mav;	
			}
		} else {
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("accountNotExistsMsg", new String("User with that email don't exists. Try another email"));
			return mav;		
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutUser(@ModelAttribute User user, HttpSession session, SessionStatus status) {
        status.setComplete();
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/users-list")
	public ModelAndView getList() {
		List userList = userService.getList();
		return new ModelAndView("list","userList",userList);
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		userService.deleteRow(id);
		return new ModelAndView("redirect:users-list");
	}
	
	@RequestMapping("/edit")
	public ModelAndView editUser(@RequestParam int id,@ModelAttribute User user) {
		User userObj = userService.getRowById(id);
		return new ModelAndView("edit","userObj",userObj);
	}
	
	@RequestMapping("/update")
	public ModelAndView updateUser(@ModelAttribute User user) {
		userService.updateRow(user);
		return new ModelAndView("redirect:users-list");
	}
	
	@RequestMapping("/info")
	public ModelAndView infoUser(@ModelAttribute User user) {
		return new ModelAndView("info");
	}

}
