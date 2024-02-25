package org.openmanager.task_service.repository;

import org.openmanager.task_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    public List<Task> findTaskByIdProject(Long idProject);
    public List<Task>deleteTaskByIdProject(Long idProject);
}
