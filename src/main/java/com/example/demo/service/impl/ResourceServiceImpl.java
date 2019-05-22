package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ResourceMapper;
import com.example.demo.domain.Resource;
import com.example.demo.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<Resource> loadAll() {
		return resourceMapper.loadAll();
	}
	
}
