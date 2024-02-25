package org.openmanager.project_service.config;

import org.openmanager.project_service.dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "task-service",url = "${application.config.task.url}")
public interface ProjectClient {
    @GetMapping("/project/{idProject}")
    List<TaskDto> getAllTaskByIdProject(@PathVariable("idProject")Long idProject);

    @DeleteMapping("/project/{idProject}")
    boolean deleteAllTaskProject(@PathVariable("idProject")Long idProject);
}
