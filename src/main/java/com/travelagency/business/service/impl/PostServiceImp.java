package com.travelagency.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelagency.business.service.PostService;
import com.travelagency.business.web.dto.PostDto;
import com.travelagency.common.util.Visibility;
import com.travelagency.data.Location;
import com.travelagency.data.Post;
import com.travelagency.data.User;
import com.travelagency.data.repository.PostRepository;


@Service
public class PostServiceImp implements PostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public void save(@Valid PostDto postDto) throws Exception {
		System.out.println(postDto.toString());
		Post post = new Post();
		post.setBody(postDto.getBody());
		post.setCreatedAt(new Date());
		post.setLastUpdatedAt(new Date());
		post.setOwner(postDto.getOwner());
		post.setVisibility(postDto.getVisibility());
		post.setLocation(new Location(1L));
		try {
			postRepository.save(post);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	@Override
	public List<Post> findAllPublicPosts(){
		return postRepository.findAllPublicPosts(Visibility.PUBLIC.toString());
	}
	@Override
	public List<Post> findAllPostsByUserId(Long id) {
		return postRepository.findAllByOwnerIdOrderByLastUpdatedAt(id);
	}

}
