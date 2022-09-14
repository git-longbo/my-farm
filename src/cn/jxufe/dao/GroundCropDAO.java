package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.jxufe.entity.GroundCrop;

public interface GroundCropDAO extends JpaRepository<GroundCrop,Long>{
	List<GroundCrop> findByUId(Long uId,Sort sort);
	List<GroundCrop> findByUId(Long uId);
	GroundCrop findByUIdAndGroundId(Long uId, int groundId);

}
