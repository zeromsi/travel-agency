package com.travelagency.business.service;

import java.util.List;

import javax.validation.Valid;

import com.travelagency.business.web.dto.PostDto;
import com.travelagency.data.Post;


public interface PostService {

	void save(@Valid PostDto postDto) throws Exception;

	List<Post> findAllPublicPosts();

	List<Post> findAllPostsByUserId(Long id);

	Post findByIdAndUserId(Long id, Long userId)  throws Exception;

}
