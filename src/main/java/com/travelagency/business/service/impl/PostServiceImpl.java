package com.travelagency.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelagency.business.service.PinnedPostService;
import com.travelagency.business.service.PostService;
import com.travelagency.business.web.dto.PostDto;
import com.travelagency.common.util.Visibility;
import com.travelagency.data.Location;
import com.travelagency.data.Post;
import com.travelagency.data.User;
import com.travelagency.data.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PinnedPostService pinnedPostService;

	@Override
	public void save(@Valid PostDto postDto) throws Exception {
		Post post = new Post();
		post.setBody(postDto.getBody().trim());
		post.setCreatedAt(new Date());
		post.setLastUpdatedAt(new Date());
		post.setOwner(postDto.getOwner());
		post.setVisibility(postDto.getVisibility());
		post.setLocation(new Location(postDto.getLocation().getId()));
		try {
			postRepository.save(post);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@Override
	public void update(@Valid PostDto postDto) throws Exception {
		Post post = postRepository.getOne(postDto.getId());
		post.setBody(postDto.getBody().trim());
		post.setLastUpdatedAt(new Date());
		post.setVisibility(postDto.getVisibility());
		post.setLocation(new Location(1L));
		try {
			postRepository.save(post);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	@Override
	public List<Post> findAllPublicPosts() {
		return postRepository.findAllPublicPosts(Visibility.PUBLIC.toString());
	}

	@Override
	public List<Post> findAllPostsByUserId(Long id) {
		List<Post> posts = new ArrayList<>();
		posts = postRepository.findAllByOwnerIdOrderByLastUpdatedAtDesc(id);
		try {
			Post pinnedPost = pinnedPostService.getPinnedPostByOwnerId(id).getPost();

			if (pinnedPost != null) {
				posts.remove(posts.indexOf(pinnedPost));
				posts.add(0, pinnedPost);
			}
		} catch (Exception e) {

		}
		return posts;
	}

	@Override
	public Post findByIdAndUserId(Long id, Long userId) throws Exception {
		try {
			Post post = postRepository.findById(id).get();
			if (post.getOwner().getId() != userId) {
				throw new Exception("Invalid Id");
			}
			return post;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
