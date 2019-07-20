package com.travelagency.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelagency.business.service.LocationService;
import com.travelagency.data.Location;
import com.travelagency.data.repository.LocationRepository;


@Service
public class LocationServiceImpl implements LocationService{

	
	@Autowired
	LocationRepository locationRepository;
	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

}
