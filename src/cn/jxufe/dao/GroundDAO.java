package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.Ground;


public interface GroundDAO  extends JpaRepository<Ground,Long>{
	List<Ground> findByCode(int code);
}
