package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.Ground;

public interface GroundService {
	 List<Ground> getAll();
	 Ground getGroundTypeByCode(int code);
	 
}
