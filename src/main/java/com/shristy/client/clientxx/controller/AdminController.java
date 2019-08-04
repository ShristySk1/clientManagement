/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.client.clientxx.controller;

import com.shristy.client.clientxx.entity.Client;
import com.shristy.client.clientxx.entity.Project;
import com.shristy.client.clientxx.repository.ClientRepository;
import com.shristy.client.clientxx.repository.ProjectRepository;
import com.shristy.client.clientxx.repository.impl.ClientServiceImpl;
import com.shristy.client.clientxx.repository.impl.ProjectServiceImpl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ClientRepository client;
   
    @Autowired
    private ProjectRepository project;

    @Autowired
    private ClientServiceImpl clientservice;
   
    @Autowired
    private ProjectServiceImpl projectservice;
    
    @GetMapping
    public String test() {
        return "client_mgmt";
    }

    @GetMapping("/view_client")
    public String viewClient(Model model) {
        model.addAttribute("client", clientservice.getAll());
        return "viewClientPage";
    }

    @GetMapping("/view_project")
    public String viewProject(Model model) {
        model.addAttribute("project", projectservice.getAll());

        return "viewProjectPage";
    }

  

    @GetMapping("/projectView")
    public String projectView(Model model) {
//          model.addAttribute("project",project.findAll());

        model.addAttribute("clients", clientservice.getAll());

        model.addAttribute("client", client.getOne(1L));
        model.addAttribute("project", project.findByClient(new Long(1)));
        return "client";
    }

    @GetMapping(value = "/sideclient/{id}")
    public String sideClient(@PathVariable("id") Long id, Model model) {
        List<Client> cl = clientservice.getAll();
        Collections.sort(cl);
        model.addAttribute("clients", cl);
//        model.addAttribute("type", projecttype.findAll());
        model.addAttribute("id", id);
        model.addAttribute("client", client.getOne(id));
        List<Project> list = project.findByClient(new Long(id));
        Collections.sort(list);
        model.addAttribute("project", list);
//        List<BigInteger> listofprojectid = project.findIdByClient(new Long(id));
//         List<String> name=new ArrayList<>();
//        for(BigInteger i :listofprojectid){
//         String n= project.findByProjectType(i.longValue());
//          name.add(n);
//        }
//     
//        model.addAttribute("projecttypename",name);
//        errrrrrrrrrrrr

        return "client";
    }

    @PostMapping(value = "/saveClient")
    public String saveClient(@ModelAttribute("addClient") Client clients, Model model) {
        Long id = client.findMaxId();
        clients.setClientId(id + (long) 1);
        clientservice.save(clients);
        return "redirect:/admin/sideclient/1";

    }

    @PostMapping(value = "/saveProject/{id}")
    public String saveProject(@PathVariable("id") Long i,@ModelAttribute("addProject") Project projects, Model model) {

        Long id = project.findMaxId();
        System.out.println("id" + id);
        projects.setProjectId(id + (long) 1);
        projectservice.save(projects);
        return "redirect:/admin/sideclient/"+i;

    }

    @PostMapping(value = "/saveEditClient")
    public String saveEditClient(@ModelAttribute("editClient") Client c, Model model) {
        clientservice.save(c);
        return "redirect:/admin/sideclient/1";

    }

    @GetMapping(value = "/deleteClient/{id}")
    public String deletetClient(@PathVariable("id") Long id, Model model) {
        
        try{
//           Long i = project.checkForChildren(id);
//            if(i!=null){
//                String message = "";
//            }
//            else{
//                client.deleteById(id);
//
//            }
//project.deleteAllChildren(id);
System.out.println("iddddddd"+id);
  client.deleteById(id);
String message = "Successfully deleted";
model.addAttribute("message",message);

        }
        catch(Exception e){
            System.out.println("error--------------------------------------"+e.getMessage());
        }
        
        model.addAttribute("clients", clientservice.getAll());

        model.addAttribute("client", client.getOne(1L));
        model.addAttribute("project", project.findByClient(new Long(1)));
        return "client";

    }
    @GetMapping("/editPage/{id}")
    public String projectView(@PathVariable("id") Long id,Model model) {
//          model.addAttribute("project",project.findAll());
Long c=project.findClientByProject(id);

    List<Client> cl = clientservice.getAll();
        Collections.sort(cl);
        model.addAttribute("clients", client.getOne(c));
//        model.addAttribute("clients",client.findById(c));

        model.addAttribute("id", c);
        model.addAttribute("client", client.getOne(c));
    
        model.addAttribute("project", project.getOne(id));
        return "client2";
    }
    
 
    @PostMapping(value = "/shared/edit")
    public String saveedit(@ModelAttribute("editt") Project p, Model model) {
//       for(Project pp: project.findAll()){
//           if(pp.getClientId()==p.getClientId()){
//               System.out.println("iddddddd of pp"+pp.getClientId());
//                System.out.println("iddddddd of p"+p.getClientId());
//               project.delete(pp);
//               
//           }
//       }
project.deleteById(p.getProjectId());
Long clientid=p.getClientId();
        projectservice.save(p);
        return "redirect:/admin/sideclient/"+clientid;

    }
        @GetMapping(value = "/deleteProject/{id}/{clientid}")
    public String deletetPro(@PathVariable("id") Long id,@PathVariable("clientid") Long clientid, Model model) {
        
        try{
project.deleteById(id);
String message = "Successfully deleted";
model.addAttribute("message",message);

        }
        catch(Exception e){
            System.out.println("error--------------------------------------"+e.getMessage());
        }
        
        model.addAttribute("clients", clientservice.getAll());

        model.addAttribute("client", client.getOne(1L));
        model.addAttribute("project", project.findByClient(new Long(1)));
     return "redirect:/admin/sideclient/"+clientid;
    }
    
}
