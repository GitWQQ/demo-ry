package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.domain.Author2;

@Service
public class AuthorService2 {
    @Autowired
    private AuthorDao authorDao;
 
    public void add(Author2 author) {
        this.authorDao.add(author);
    }  
    public void update(Author2 author) {
        this.authorDao.update(author);      
    }
    public void delete(Long id) {
        this.authorDao.delete(id);
    }
    public Author2 findAuthor(Long id) {
        return this.authorDao.findAuthor(id);
    } 
    public List<Author2> findAuthorList() {
        return this.authorDao.findAuthorList();
    }
}