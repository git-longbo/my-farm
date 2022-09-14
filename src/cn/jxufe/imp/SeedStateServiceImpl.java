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
import cn.jxufe.dao.SeedStateDAO;
import cn.jxufe.entity.SeedState;
import cn.jxufe.service.SeedStateService;
@Service
public class SeedStateServiceImpl implements SeedStateService{
	@Autowired
	private SeedStateDAO seedStateDAO;
	@Override
	public SeedState getStateBySeedIdandGrowState(int seedId,int growState) {
		SeedState list = seedStateDAO.findBySeedIdAndGrowState(seedId,growState);
		return list;
	}
	@Override
	public EasyUIData<?> listPage (int seedId,EasyUIDataPageRequest easyUIDataPageRequest) {
		EasyUIData<SeedState> result = new EasyUIData<SeedState>();
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
	    if(easyUIDataPageRequest.getOrder().equals("asc")) {
	        orders.add(new Sort.Order(Direction.ASC,easyUIDataPageRequest.getSort()));
	    }else {
	        orders.add(new Sort.Order(Direction.DESC,easyUIDataPageRequest.getSort()));
	    }
	    Pageable pageRequest =new PageRequest(easyUIDataPageRequest.getPage()-1,
			easyUIDataPageRequest.getRows(), 
			new Sort(orders));     
	    Page<SeedState> curPage = seedStateDAO.findBySeedId(seedId,pageRequest);
	    result.setRows(curPage.getContent());
	    result.setTotal(curPage.getTotalElements());
	    return result;
	}
	@Override
	public Message delete(SeedState seedState) {
		Message message = new Message();
		try {
		seedStateDAO.delete(seedState);
		message.setCode(0);
		message.setMsg("½×¶ÎÉ¾³ý³É¹¦");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("½×¶ÎÉ¾³ýÊ§°Ü");
		}
		return message;
	}
	@Override
	public Message save(SeedState seedState) {
		Message message = new Message();
		try {
		seedStateDAO.save(seedState);
		message.setCode(0);
		message.setMsg("½×¶Î±£´æ³É¹¦");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("½×¶Î±£´æÊ§°Ü");
		}
		return message;
		
	}
	@Override
	public SeedState findBySIdAndStaus(int seedId, int sStatus) {	
		return seedStateDAO.findBySeedIdAndGrowState(seedId, sStatus);
	}

}
