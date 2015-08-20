package com.github.skurski.controller;

import java.util.List;
import java.util.Set;

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

import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;
import com.github.skurski.domain.UserLogin;
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
	public ModelAndView getLogin(@ModelAttribute UserLogin userLogin) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("userLogin", new UserLogin());
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView authUser(@Valid @ModelAttribute UserLogin userLogin, BindingResult result) {
		if(result.hasErrors()) return new ModelAndView("login");
		
		User validUser = userService.getUserByEmail(userLogin.getEmail());
		if(validUser != null) {
			if(userLogin.getPassword().equals(validUser.getPassword())) {
				ModelAndView mav = new ModelAndView("redirect:tour-list");
				mav.addObject("userId", validUser);
				Set<Travel> travelObj = validUser.getTravel();
				System.out.println(travelObj);
				mav.addObject("travelObj", travelObj);
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
	
	@RequestMapping("/tour-list")
	public ModelAndView getTour() {
		List userList = userService.getList();
		return new ModelAndView("tour","userList",userList);
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
