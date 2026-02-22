package com.smk.apiproject.services;

import com.smk.apiproject.models.Task;
import com.smk.apiproject.models.User;
import com.smk.apiproject.repositories.TaskRepository;
import com.smk.apiproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    private Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado " + id + ", Tipo: " + Task.class.getName()
        ));
    }
    @Transactional
    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){

        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
