package cn.jxufe.service;

import java.util.List;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.GroundCrop;

public interface GroundCropService {

	List<GroundCrop> findByUserId(long id);

	GroundCrop findByUIdAndGroundId(long getuId, int groundId);

	Message plant(int groundId, int sId);

	Message receive(GroundCrop ground);

	Message killWorm(GroundCrop ground);

	Message cleanLand(GroundCrop ground);
	
	List<GroundCrop> finAll(long uId);
}
