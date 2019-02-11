package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthorMapper;
import com.example.demo.domain.Author;
import com.example.demo.service.ThmeleafService;

@Service
public class ThmeleafServiceImpl implements ThmeleafService {

	@Autowired 
	private AuthorMapper authorMapper;
	@Override
	public List getAllRecord() {
		List result=authorMapper.getAllRecord();
		return result;
	}
	@Override
	public Author findAuthor(Integer id) {	
		return this.authorMapper.findAuthor(id);
	}

}
