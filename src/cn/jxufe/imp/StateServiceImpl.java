package cn.jxufe.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.StateDAO;
import cn.jxufe.entity.State;
import cn.jxufe.service.StateService;
@Service
public class StateServiceImpl implements StateService{
	@Autowired
	private StateDAO stateDAO;
	@Override
	public List<State> getAll() {
		
		return stateDAO.findAll();
	}

	@Override
	public State getStateByCode(int code) {
		List<State> list = stateDAO.findByCode(code);
		return list.get(0);
	}
	
}
