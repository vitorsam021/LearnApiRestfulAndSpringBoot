package com.smk.apiproject.services;

import com.smk.apiproject.models.User;
import com.smk.apiproject.repositories.TaskRepository;
import com.smk.apiproject.repositories.UserRepository;
import com.smk.apiproject.services.exceptions.DataBindingViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow( () -> new RuntimeException(
                "Usuário não encontrado " + id + ", Tipo: " + User.class.getName()
        ));
    }
    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    public User update(User obj){

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);

    }

    public void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch(Exception e){
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
