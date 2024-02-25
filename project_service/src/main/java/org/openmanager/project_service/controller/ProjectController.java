package org.openmanager.project_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.openmanager.project_service.dto.ProjectDto;
import org.openmanager.project_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private ProjectService projectService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProject(@RequestBody ProjectDto projectDto){
        return projectService.createProject(projectDto);
    }

    @GetMapping("/")
    public List<ProjectDto> getAllProject(){
        return projectService.getAllProject();
    }

    @DeleteMapping("/{id}")
    public String deleteAllProject(@PathVariable("id")Long id){
        return projectService.deleteProject(id);
    }

}
