package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthorMapper;
import com.example.demo.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorMapper authorMapper;

	@Override
	public Author findAuthor(Integer id) {
		return this.authorMapper.findAuthor(id);
	}
	
}
