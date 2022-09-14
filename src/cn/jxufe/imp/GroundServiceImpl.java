package cn.jxufe.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.GroundDAO;
import cn.jxufe.entity.Ground;
import cn.jxufe.service.GroundService;
@Service
public class GroundServiceImpl implements GroundService{
	@Autowired GroundDAO groundDAO;
	@Override
	public List<Ground> getAll() {
		
		return groundDAO.findAll();
	}
	@Override
	public Ground getGroundTypeByCode(int code) {
		List<Ground> list =  groundDAO.findByCode(code);
		
		return list.get(0);
	}

}
