package cn.jxufe.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.SeedTypeDAO;
import cn.jxufe.entity.SeedType;
import cn.jxufe.service.SeedTypeService;
@Service
public class SeedTypeServiceImpl implements SeedTypeService{
	@Autowired SeedTypeDAO seedTypeDAO;
	@Override
	public List<SeedType> getAll() {
		
		return seedTypeDAO.findAll();
	}
	@Override
	public SeedType getSeedTypeByCode(int code) {
		List<SeedType> list = seedTypeDAO.findByCode(code);
		return  list.get(0);
	}

}
