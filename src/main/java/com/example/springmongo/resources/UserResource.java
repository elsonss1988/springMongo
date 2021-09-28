package com.example.springmongo.resources;

import com.example.springmongo.dto.UserDTO;
import com.example.springmongo.entities.Post;
import com.example.springmongo.entities.User;
import com.example.springmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
//        User maria = new User("1","Maria Silva","maria@gmail.com");
//        User alex = new User("2","Maria Silva","maria@gmail.com");
//        List<User> list = new ArrayList<>();
//        list.addAll(Arrays.asList(maria,alex));
        List<User> list= service.findAll();
        List<UserDTO> listDto=list.stream().map(x->new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user= service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User obj= service.fromDTO(objDto);
        obj=service.insert(obj);
        URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO objDto){
        User obj=service.fromDTO(objDto);
        obj.setId(id);
        obj=service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value="/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj= service.findById(id);
        return  ResponseEntity.ok().body(obj.getPosts());
    }
}
