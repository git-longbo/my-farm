package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.State;

public interface StateDAO  extends JpaRepository<State,Long>{
	List<State> findByCode(int code);
}
