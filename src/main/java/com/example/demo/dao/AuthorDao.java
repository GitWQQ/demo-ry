package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Author2;

@Repository
public class AuthorDao {

	 @Autowired
	    private MongoTemplate mongoTemplate;
	 
	    public void add(Author2 author) {
	        this.mongoTemplate.insert(author);
	    }
	    public void update(Author2 author) {
	        this.mongoTemplate.save(author); 
	    }
	    public void delete(Long id) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where("_id").is(id));
	        this.mongoTemplate.remove(query, Author2.class);
	    }
	    public Author2 findAuthor(Long id) {
	        return this.mongoTemplate.findById(id, Author2.class);
	    }
	    public List<Author2> findAuthorList() {
	        Query query = new Query();
	        return this.mongoTemplate.find(query, Author2.class);
	    }
}
