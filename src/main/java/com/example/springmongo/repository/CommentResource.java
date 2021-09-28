package com.example.springmongo.repository;

import com.example.springmongo.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentResource extends MongoRepository<Comment,String> {
}
