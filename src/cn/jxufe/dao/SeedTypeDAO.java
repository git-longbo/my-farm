package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.SeedType;

public interface SeedTypeDAO  extends JpaRepository<SeedType,Long>{
	List<SeedType> findByCode(int code);
}
