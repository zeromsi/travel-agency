package com.travelagency.business.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.travelagency.business.service.LocationService;
import com.travelagency.business.service.PostService;
import com.travelagency.business.service.UserService;

@Controller
public class MainController {
	@Autowired
	PostService postService;

	@Autowired
	UserService userService;
	
	@Autowired
	LocationService locationService;
 

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("locations", locationService.findAll());

		model.addAttribute("posts", postService.findAllPublicPosts());
		return "index";
	}

    
    @GetMapping("/profile")
    public String profile(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("posts", postService.findAllPostsByUserId(userService.findByEmail(userDetails.getUsername()).getId()));
        return "profile";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}
