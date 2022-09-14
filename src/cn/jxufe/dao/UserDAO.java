package cn.jxufe.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.User;



public interface UserDAO extends JpaRepository<User,Long>{
	public Page<User> findByUsernameLike(String username,Pageable page);
	public User findByNicheng(String nicheng);
}
