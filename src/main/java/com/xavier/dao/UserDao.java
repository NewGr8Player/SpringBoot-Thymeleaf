package com.xavier.dao;

import com.xavier.bean.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 用户Dao
 * @author NewGr8Player
 */
public interface UserDao extends CrudRepository<User, String> {

    /**
     * 根据姓名查找用户
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
