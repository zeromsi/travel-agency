package com.travelagency.business.service;

import com.travelagency.data.PinnedPost;

public interface PinnedPostService {

	void save(PinnedPost pinnedPost) throws Exception;

	PinnedPost getPinnedPostByOwnerId(Long userId);

}
