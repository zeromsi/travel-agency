package com.travelagency.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PinnedPost {
	
	@Id
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post")
	private Post post;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner")
	private User owner;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PinPost [id=" + id + ", post=" + post + ", owner=" + owner + "]";
	}

	public PinnedPost(Post post, User owner) {
		super();
		this.id=owner.getId();
		this.post = post;
		this.owner = owner;
	}

	public PinnedPost() {
		super();
	}

	
	
}
