/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.client.clientxx.repository;


import com.shristy.client.clientxx.entity.Client;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
 @Query(value ="select max(client_id) from client" ,nativeQuery = true)
public Long findMaxId();
   
// @Query(value ="delete from client c where c.client_id = ?1" ,nativeQuery = true)
 @Override
public void deleteById(Long id);

//@Modifying
//@Query(value = "DELETE FROM client c USING project p WHERE  c.client_id = p.client_id AND p.client_id = ?1",nativeQuery = true)
//void deleteIdClient(Long id);
}
