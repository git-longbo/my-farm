package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.entity.Seed;
import cn.jxufe.service.SeedService;
@Service
public class SeedServiceImpl implements SeedService{
	@Autowired
	private  SeedDAO seedDAO;
	@Override
	public EasyUIData<?> listPage(String seedName,EasyUIDataPageRequest easyUIDataPageRequest) {
		EasyUIData<Seed> result = new EasyUIData<Seed>();
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
	    if(easyUIDataPageRequest.getOrder().equals("asc")) {
	        orders.add(new Sort.Order(Direction.ASC,easyUIDataPageRequest.getSort()));
	    }else {
	        orders.add(new Sort.Order(Direction.DESC,easyUIDataPageRequest.getSort()));
	    }
	    Pageable pageRequest =new PageRequest(easyUIDataPageRequest.getPage()-1,
			easyUIDataPageRequest.getRows(), 
			new Sort(orders));     
	    seedName = '%'+seedName+'%';
	    Page<Seed> curPage = seedDAO.findBySeedNameLike(seedName,pageRequest);
	    result.setRows(curPage.getContent());
	    result.setTotal(curPage.getTotalElements());
	    return result;
	}
	@Override
	public Message save(Seed seed) {
		Message message = new Message();
		try {
		seedDAO.save(seed);
		message.setCode(0);
		message.setMsg("种子保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("种子保存失败");
		}
		return message;
	}

	@Override
	public Message delete(Seed seed) {
		Message message = new Message();
		try {
		seedDAO.delete(seed);
		message.setCode(0);
		message.setMsg("种子删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("种子删除失败");
		}
		return message;
	}

	
	

}
