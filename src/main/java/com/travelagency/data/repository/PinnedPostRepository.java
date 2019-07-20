package com.travelagency.data.repository;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelagency.data.PinnedPost;

@Repository
public interface PinnedPostRepository extends JpaRepository<PinnedPost,Long>{

	Future<PinnedPost> findByOwnerId(Long userId);
	
}


