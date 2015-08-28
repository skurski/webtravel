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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.skurski.domain.Gallery;
import com.github.skurski.domain.Travel;
import com.github.skurski.domain.User;
import com.github.skurski.domain.UserLogin;
import com.github.skurski.services.GalleryService;
import com.github.skurski.services.TravelService;
import com.github.skurski.services.UserService;

@Controller
@SessionAttributes({"travelId", "user"})
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TravelService travelService;
	
	@Autowired
	GalleryService galleryService;
	
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
	
	@RequestMapping("/users-list")
	public ModelAndView getList() {
		List userList = userService.getList();
		return new ModelAndView("list","userList",userList);
	}
	
	@RequestMapping(value="/tour-list", method=RequestMethod.GET)
	public ModelAndView getTour(@ModelAttribute Travel travel, HttpSession session) {
		ModelAndView mav = new ModelAndView("tour");
        User userFromSession = (User) session.getAttribute("user");
		User user = userService.getRowById(userFromSession.getId());
		mav.addObject("user", user);
		Set<Travel> travelSet = user.getTravel();
		if(travelSet.isEmpty()) {
			mav.addObject("noTravel", new String("You have no travel"));
		} else {
			for(Travel trav: travelSet) {
				Set<Gallery> gallerySet = trav.getGallery();
				if(gallerySet.iterator().hasNext()) {
					trav.setThumbnail(gallerySet.iterator().next().getPath());
				}
			}
			mav.addObject("travelSet", travelSet);
		}
		return mav;		
	}
	
	@RequestMapping("/delete-tour")
	public ModelAndView deleteTour(@RequestParam int travelId) {
		travelService.deleteRow(travelId);
		return new ModelAndView("redirect:tour-list");
	}
	
	@RequestMapping("/edit-account")
	public ModelAndView editUser(HttpSession session) {
		User userFromSession = (User) session.getAttribute("user");
		User user = userService.getRowById(userFromSession.getId());
		return new ModelAndView("edit_account","userObj",user);
	}
	
	@RequestMapping(value="/edit-tour", method=RequestMethod.GET)
	public ModelAndView editTravel(@RequestParam int travelId) {
		ModelAndView mav = new ModelAndView("edit_travel");
		mav.addObject("travelId", travelId);
		Travel travel = travelService.getRowById(travelId);
		Set<Gallery> gallerySet = travel.getGallery();
		mav.addObject("gallerySet", gallerySet);
		mav.addObject("travel", travel);
		return mav;
	}
	
	@RequestMapping(value="/edit-tour", method=RequestMethod.POST)
	public ModelAndView updateTravel(@ModelAttribute Travel travel, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int travelId = (int) session.getAttribute("travelId");
		travel.setUser(user); 
		travel.setId(travelId); 
		travelService.updateRow(travel);
		return new ModelAndView("edit_travel");
	}
	
	@RequestMapping("/update")
	public ModelAndView updateUser(@ModelAttribute User user) {
		userService.updateRow(user);
		return new ModelAndView("redirect:users-list");
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
	
	private ModelAndView redirectInvalidUser(HttpSession session) {
		if(null == session.getAttribute("user")){     
			return new ModelAndView("redirect:/");  
		} else {
			return null;
		}
	}
	
}
