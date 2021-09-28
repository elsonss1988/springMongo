package com.example.springmongo.services;

import com.example.springmongo.entities.Post;
import com.example.springmongo.repository.PostRepository;
import com.example.springmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado "+id));
    }

    public List<Post> findAll(){
        return repository.findAll();
    }

    public List<Post> findByTitle(String text){
        return repository.findByTitleContainingIgnoraingCase(text);
    }

}
