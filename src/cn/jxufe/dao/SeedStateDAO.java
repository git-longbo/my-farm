package cn.jxufe.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.SeedState;

public interface SeedStateDAO extends JpaRepository<SeedState,Long>{
	SeedState findBySeedIdAndGrowState(int seedId,int growState);
	Page<SeedState> findBySeedId(int seedId,Pageable page);

}
