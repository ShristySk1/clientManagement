/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.client.clientxx.repository;


import com.shristy.client.clientxx.entity.Project;
import java.math.BigInteger;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{
  @Query(value="select p.project_id,p.name,p.description,p.start_date,p.end_date,p.client_id  from project p where p.client_id =?1",nativeQuery=true)
 public List<Project> findByClient(Long id);

 @Query(value ="select max(project_id) from project" ,nativeQuery = true)
public Long findMaxId();

 @Query(value ="select c.client_id from client c where c.client_id= ?1",nativeQuery = true)
public Long checkForChildren(Long id);

@Query(value ="delete from project p where p.client_id= ?1",nativeQuery = true)
public void deleteAllChildren(Long id);


@Query(value="select p.project_id from project p where p.client_id =?1",nativeQuery=true)
 public List<BigInteger> findIdByClient(Long id);

@Query(value="select p.client_id from project p where p.project_id =?1",nativeQuery=true)
 public Long findClientByProject(Long id);
}