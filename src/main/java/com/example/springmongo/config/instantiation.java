package com.example.springmongo.config;

import com.example.springmongo.dto.AuthorDTO;
import com.example.springmongo.dto.CommentDTO;
import com.example.springmongo.entities.Post;
import com.example.springmongo.entities.User;
import com.example.springmongo.repository.PostRepository;
import com.example.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();
        User maria = new User(null, "Maria White", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1=new Post(null, Instant.parse("2018-11-30T18:35:24.00Z"),"Partiu Viagem","vou viajar para SP",new AuthorDTO(maria));
        Post post2=new Post(null, Instant.parse("2018-03-23T18:35:24.00Z"),"Bom dia","Acorda SP",new AuthorDTO(alex));

        CommentDTO c1 =new CommentDTO("Boa Viagem mano", Instant.parse("2021-03-20T18:35:24.00Z"),new AuthorDTO(alex));
        CommentDTO c2 =new CommentDTO("Aproveitei", Instant.parse("2023-03-27T18:35:24.00Z"),new AuthorDTO(bob));
        CommentDTO c3 =new CommentDTO("Tenha um otimo dia", Instant.parse("2021-03-21T18:35:24.00Z"),new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);


    }
}
