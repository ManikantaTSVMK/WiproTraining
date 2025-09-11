package com.hcl.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.gl.pojo.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>
{

}

//JpaRepository: inbuilt methods to perform direct operations of database
//findAll
//save
//saveAll
//findById
//existsById
//deleteById

//custom methods and custom queries based on POJO
