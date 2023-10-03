package com.fastcampus.ch4.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fastcampus.ch4.entity.Board;
import com.fastcampus.ch4.entity.Board2;
import com.fastcampus.ch4.entity.User2;

public interface UserRepository2 extends CrudRepository<User2, String> {


}
