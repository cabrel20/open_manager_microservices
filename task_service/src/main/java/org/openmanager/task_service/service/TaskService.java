package org.openmanager.task_service.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.openmanager.task_service.dto.TaskDto;
import org.openmanager.task_service.entity.Task;
import org.openmanager.task_service.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    // create task;
    public String createTask(TaskDto taskDto){
        Task task = Task.builder()
                .content(taskDto.getContent())
                .idProject(taskDto.getIdProject())
                .build();
        taskRepository.save(task);
        return "Tasks is created";
    }

    // get all tasks by idProject
    public List<TaskDto> getAllTaskByIdProject(Long idProject){
        List < Task> taskList = taskRepository.findTaskByIdProject(idProject);
        return taskList.stream().map(this::matToTask).toList();
    }

    // map to task function
    private TaskDto matToTask(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .content(task.getContent())
                .build();
    }
    
    // delete task
    public String deleteTask(Long id){
        taskRepository.deleteById(id);
        return "task is deleted";
    }

    @Transactional
    public boolean deleteAllTaskProject(Long idProject){
        List<Task> taskList = taskRepository.deleteTaskByIdProject(idProject);
        return taskList.isEmpty();
    }
}
