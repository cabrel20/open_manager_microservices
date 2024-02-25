package org.openmanager.task_service.controller;

import org.openmanager.task_service.dto.TaskDto;
import org.openmanager.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private TaskService taskService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }

    @GetMapping("/project/{idProject}")
    public List<TaskDto> getAllTaskByIdProject(@PathVariable("idProject") Long idProject){
        return taskService.getAllTaskByIdProject(idProject);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id")Long id){
        return taskService.deleteTask(id);
    }

    @DeleteMapping("/project/{idProject}")
    public boolean deleteAllTaskProject(@PathVariable("idProject")Long idProject){
        return taskService.deleteAllTaskProject(idProject);
    }
}
