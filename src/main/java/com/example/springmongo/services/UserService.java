package com.example.springmongo.services;

import com.example.springmongo.dto.UserDTO;
import com.example.springmongo.entities.User;
import com.example.springmongo.repository.UserRepository;
import com.example.springmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> obj=repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto Nao Encontrado "+id));
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj,obj);
        return repository.save(obj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setName(obj.getEmail());
    }



    public User insert(User obj){
        return repository.save(obj);
    }

}
