package com.travelagency.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelagency.business.service.PinnedPostService;
import com.travelagency.data.PinnedPost;
import com.travelagency.data.repository.PinnedPostRepository;

@Service
public class PinnedPostServiceImpl implements PinnedPostService{

	
	@Autowired
	PinnedPostRepository pinnedPostRepository;
	
	@Override
	public void save(PinnedPost pinnedPost) throws Exception {
		try {
			pinnedPostRepository.save(pinnedPost);
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	@Override
	public PinnedPost getPinnedPostByOwnerId(Long userId) {
		try {
			return pinnedPostRepository.findByOwnerId(userId).get();
		}catch(Exception e) {
			return null;
		}
	}
}
