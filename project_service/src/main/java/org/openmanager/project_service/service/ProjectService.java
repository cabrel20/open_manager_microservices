package org.openmanager.project_service.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.openmanager.project_service.config.ProjectClient;
import org.openmanager.project_service.dto.ProjectDto;
import org.openmanager.project_service.dto.TaskDto;
import org.openmanager.project_service.entity.Project;
import org.openmanager.project_service.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private ProjectClient projectClient;


    // create project
    public String createProject(ProjectDto projectDto){
        Project project = Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .date(projectDto.getDate())
                .build();
        projectRepository.save(project);
        return "project has created";
    }

    // get all project
    public List<ProjectDto> getAllProject(){
       List<Project> projects = projectRepository.findAll();
       List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            List<TaskDto> taskDtoList = projectClient.getAllTaskByIdProject(project.getId());
            ProjectDto projectDto = ProjectDto.builder()
                    .name(project.getName())
                    .description(project.getDescription())
                    .date(project.getDate())
                    .taskDtoList(taskDtoList)
                    .id(project.getId())
                    .build();
            projectDtos.add(projectDto);
        }

        return projectDtos;
    }

    // delete project

    @Transactional
    public String deleteProject(Long id){
        String projectIsDelete = null;
        boolean allTaskProjectIsDeleted = projectClient.deleteAllTaskProject(id);
        if(allTaskProjectIsDeleted){
            projectRepository.deleteById(id);
           projectIsDelete = "project is deleted" ;
        }
       return projectIsDelete;
    }
}

