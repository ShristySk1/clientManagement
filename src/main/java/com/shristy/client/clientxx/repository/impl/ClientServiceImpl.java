/*
 * Cliento change this license header, choose License Headers in Project Properties.
 * Cliento change this template file, choose Clientools | Clientemplates
 * and open the template in the editor.
 */
package com.shristy.client.clientxx.repository.impl;


import com.shristy.client.clientxx.entity.Client;
import com.shristy.client.clientxx.repository.ClientRepository;
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
public class ClientServiceImpl implements GenericService<Client>{
    @Autowired
private  ClientRepository gr;

    public ClientServiceImpl() {
    }
 
    @Override
    public List<Client> getAll() {
return gr.findAll();
    }

    @Override
    public Client getById(Long id) {
return (Client)gr.getOne(id);
    }
    @Override
    public void save(Client model) {
gr.save(model);
    }
    @Override
    public boolean delete(Long id) {
     Client entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
    
}
