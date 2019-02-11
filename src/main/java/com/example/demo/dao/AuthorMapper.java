package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Author;

public interface AuthorMapper {
	 Author findAuthor(@Param("id") Integer id); 
	 public List getAllRecord();
}
