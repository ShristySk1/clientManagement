/*
 * Projecto change this license header, choose License Headers in Project Properties.
 * Projecto change this template file, choose Projectools | Projectemplates
 * and open the template in the editor.
 */



package com.shristy.client.clientxx.repository.impl;

import com.shristy.client.clientxx.entity.Project;
import com.shristy.client.clientxx.repository.ProjectRepository;
import com.shristy.client.clientxx.service.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ProjectServiceImpl implements GenericService<Project>{
    @Autowired
private  ProjectRepository gr;

    public ProjectServiceImpl() {
    }
 
    @Override
    public List<Project> getAll() {
return gr.findAll();
    }

    @Override
    public Project getById(Long id) {
return (Project)gr.getOne(id);
    }
    @Override
    public void save(Project model) {
gr.save(model);
    }
    @Override
    public boolean delete(Long id) {
     Project entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
    
}
