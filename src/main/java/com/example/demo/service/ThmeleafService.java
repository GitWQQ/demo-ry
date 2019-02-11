package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Author;

public interface ThmeleafService {
	
	public Author findAuthor(Integer id);
	public List getAllRecord();
}
