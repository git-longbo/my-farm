package cn.jxufe.service;

import java.util.List;

import cn.jxufe.entity.State;

public interface StateService {
	List<State> getAll();
	State getStateByCode(int code);
}
