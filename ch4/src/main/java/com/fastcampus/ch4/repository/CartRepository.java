package com.fastcampus.ch4.repository;

import org.springframework.data.repository.CrudRepository;

import com.fastcampus.ch4.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

	
}
