package cn.jxufe.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.SeedBag;

public interface SeedBagDAO extends JpaRepository<SeedBag,Long>{

	SeedBag findByUsernameAndSeedId(String username, int seedId);

	Page<SeedBag> findByUsername(String username,Pageable page);
}
