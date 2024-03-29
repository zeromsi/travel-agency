package com.travelagency.business.web.dto;

import javax.validation.constraints.NotEmpty;

import com.travelagency.data.Location;
import com.travelagency.data.User;


public class PostDto {
	private Long id;
	
	@NotEmpty
	private String body;

	private String visibility; 
	
	private User owner;
	
	//private Long locationId;
	
	private Location location;
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
	
	public Long getId() {
		return id;
	}

//	@Override
//	public String toString() {
//		return "PostDto [id=" + id + ", body=" + body + ", visibility=" + visibility + ", owner=" + owner
//				+ ", locationId=" + locationId + "]";
//	}
//
//	public Long getLocationId() {
//		return locationId;
//	}
//
//	public void setLocationId(Long locationId) {
//		this.locationId = locationId;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PostDto [id=" + id + ", body=" + body + ", visibility=" + visibility + ", owner=" + owner
				+ ", location=" + location + "]";
	}



}
