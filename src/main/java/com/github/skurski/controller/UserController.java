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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.Gallery;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;
import com.github.skurski.domain.UserLogin;
import com.github.skurski.services.GalleryServiceImpl;
import com.github.skurski.services.TravelServiceImpl;
import com.github.skurski.services.UserServiceImpl;

@Controller
@SessionAttributes({"travelId", "user"})
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	TravelServiceImpl travelService;
	
	@Autowired
	GalleryServiceImpl galleryService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView getLogin(@ModelAttribute UserLogin userLogin) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("userLogin", new UserLogin());
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView authUser(@Valid @ModelAttribute UserLogin userLogin, BindingResult result) {
		if(result.hasErrors()) return new ModelAndView("login");
		
		User validUser = userService.getObjectByString("email", userLogin.getEmail());
		if(validUser != null) {
			if(userLogin.getPassword().equals(validUser.getPassword())) {
				ModelAndView mav = new ModelAndView("redirect:tour-list");
				mav.addObject("user", validUser);
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
	
	@RequestMapping(value="/tour-list", method=RequestMethod.GET)
	public ModelAndView getTour(@ModelAttribute Travel travel, HttpSession session) {
		if(session.getAttribute("user") == null) return new ModelAndView("redirect:/");
		
		ModelAndView mav = new ModelAndView("tour");
        User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		List<Travel> travelList = travelService.getListByRelatedObject(user, "user");
		if(travelList.isEmpty()) {
			mav.addObject("noTravel", new String("You have no travel"));
		} else {
			for(Travel trav: travelList) {
				List<Gallery> galleryList = galleryService.getListByRelatedObject(trav, "travel");
				if(!galleryList.isEmpty()) {
					trav.setThumbnail(galleryList.get(0).getPath());
				}
			}
			mav.addObject("travelList", travelList);
		}
		return mav;		
	}
	
	@RequestMapping("/delete-tour")
	public ModelAndView deleteTour(@RequestParam int travelId, HttpSession session) {
		if(session.getAttribute("user") == null) return new ModelAndView("redirect:/");

		travelService.deleteRow(travelId);
		return new ModelAndView("redirect:tour-list");
	}
	
	@RequestMapping("/edit-account")
	public ModelAndView editUser(HttpSession session) {
		if(session.getAttribute("user") == null) return new ModelAndView("redirect:/");

		User user = (User) session.getAttribute("user");
		return new ModelAndView("edit_account","userObj",user);
	}
	
	@RequestMapping(value="/edit-account", method=RequestMethod.POST)
	public ModelAndView updateAccount(@ModelAttribute User user, BindingResult result, HttpSession session) {
		User userFromSession = (User) session.getAttribute("user");
        user.setId(userFromSession.getId());
		userService.updateRow(user);
		return new ModelAndView("edit_account");
	}
	
	@RequestMapping(value="/edit-tour", method=RequestMethod.GET)
	public ModelAndView editTravel(@RequestParam int travelId, HttpSession session) {
		if(session.getAttribute("user") == null) return new ModelAndView("redirect:/");

		ModelAndView mav = new ModelAndView("edit_travel");
		Travel travel = travelService.getRowById(travelId);
		System.out.println("works...");
		List<Gallery> galleryList = galleryService.getListByRelatedObject(travel, "travel");
		mav.addObject("travelId", travelId);
		mav.addObject("travel", travel);
		mav.addObject("galleryList", galleryList);
		return mav;
	}
	
	@RequestMapping(value="/edit-tour", method=RequestMethod.POST)
	public ModelAndView updateTravel(@ModelAttribute Travel travel, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int travelId = (int) session.getAttribute("travelId");
		travel.setUser(user); 
		travel.setId(travelId); 
		travelService.updateRow(travel);
		return new ModelAndView("redirect:edit-tour");
	}
		
	@RequestMapping(value="/addTravel", method=RequestMethod.POST)
	public ModelAndView addTravel(@ModelAttribute Travel travel, HttpSession session) {
        User user = (User) session.getAttribute("user");
        travel.setUser(user);
		travelService.insertRow(travel);
		return new ModelAndView("redirect:tour-list");
	}
	
    @RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public ModelAndView handleFileUpload(@ModelAttribute Gallery gallery, HttpSession session,
    		@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
    	
    	ModelAndView mav = new ModelAndView("redirect:edit-tour");
    	Upload upload = new Upload();
    	String[] fileName = upload.run(name, file);
    	if (fileName == null) {
    		return mav;
    	}
		int travelId = (int) session.getAttribute("travelId");
		Travel travel = travelService.getRowById(travelId);
    	gallery.setTravel(travel);
    	gallery.setPath("resources/upload/"+fileName[0]);
    	gallery.setTitle(fileName[1]);
    	galleryService.insertRow(gallery);
    	
    	return mav;
    }
	
}
