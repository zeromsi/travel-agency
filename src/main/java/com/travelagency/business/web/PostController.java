package com.travelagency.business.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelagency.business.service.LocationService;
import com.travelagency.business.service.PostService;
import com.travelagency.business.service.UserService;
import com.travelagency.business.web.dto.PostDto;
import com.travelagency.data.Post;
import com.travelagency.data.User;

@Controller
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	LocationService locationService;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("locations", locationService.findAll());

		model.addAttribute("posts", postService.findAllPublicPosts());
		return "index";
	}

	@PostMapping("/post")
	public String post(@ModelAttribute("post") @Valid PostDto postDto, BindingResult result) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		postDto.setOwner(userService.findByEmail(userDetails.getUsername()));
		try {
			postService.save(postDto);
			return "redirect:";
		} catch (Exception e) {
			return "redirect:/?failed";
		}

	}

	@GetMapping("/post")
	public String edit(@RequestParam(value = "id", required = false) Long id, Model model) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		try {
			Post post = postService.findByIdAndUserId(id, userService.findByEmail(userDetails.getUsername()).getId());
			model.addAttribute("post", post);
			model.addAttribute("locations", locationService.findAll());
		} catch (Exception e) {
			return "redirect:";
		}
		return "edit";
	}

	@PutMapping("/post")
	public String update(@RequestParam(value = "id", required = false) Long id,
			@ModelAttribute("post") @Valid PostDto postDto, BindingResult result) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findByEmail(userDetails.getUsername());
		try {
			Post post = postService.findByIdAndUserId(id, user.getId());
			postDto.setOwner(user);
			postService.save(postDto);
			return "redirect:";
		} catch (Exception e) {
			return "redirect:/?failed";
		}

	}

}
