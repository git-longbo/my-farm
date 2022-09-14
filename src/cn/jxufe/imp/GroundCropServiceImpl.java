package cn.jxufe.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.GroundCropDAO;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.GroundCrop;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.GroundCropService;
@Service
public class GroundCropServiceImpl implements GroundCropService{
	@Autowired
	GroundCropDAO groundCropDAO;
	@Override
	public List<GroundCrop> findByUserId(long id) {
		
		return groundCropDAO.findByUId(id);
	}
	
	@Autowired
	private SeedDAO seedDAO;
	
	@Autowired 
	private UserDAO userDAO;
	
	
	@Autowired
	private SeedBagDAO seedBagDAO;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public List<GroundCrop> finAll(long uId) {
		return groundCropDAO.findByUId(uId);
	}

	@Override
	public GroundCrop findByUIdAndGroundId(long uId, int groundId) {
		return groundCropDAO.findByUIdAndGroundId(uId,groundId);
	}

	@Override
	public Message plant(int groundId,int sId) {
		Message message = new Message();
		
        try {
        	User user = (User) request.getSession().getAttribute("user");
        	
        	SeedBag bag = seedBagDAO.findByUsernameAndSeedId(user.getUsername(), sId);
        	Seed seed = seedDAO.findBySeedId(sId);
        	
        	if(bag.getNum() == 0) {
        		message.setCode(-10);
                message.setMsg("当前种子数量不足");	
                return message;
        	}
        	GroundCrop ground = groundCropDAO.findByUIdAndGroundId(user.getId(), groundId);
        	if(ground == null) {	
        		GroundCrop ground2 = new GroundCrop();
        		ground2.setId(0);
        		ground2.setuId(user.getId());
        		ground2.setGroundId(groundId);
        		ground2.setsId(sId);
        		ground2.setGroundStatus(1);
        		ground2.setsStatus(0);
        		ground2.setNowSeason(1);
        		ground2.setHasInsect(0);
        		ground2.setPlantTime(System.currentTimeMillis());
            	ground2.setTrueHarvestNum(seed.getReceiveNum());
            	groundCropDAO.save(ground2);
            	
        	}else {
        		//土地进行修改
            	ground.setsId(sId);
            	ground.setGroundStatus(1);
            	ground.setsStatus(0);
            	ground.setHasInsect(0);
            	ground.setPlantTime(System.currentTimeMillis());
            	ground.setTrueHarvestNum(seed.getReceiveNum());
            	groundCropDAO.save(ground);   	
        	}
        	//修改储存数量
        	bag.setNum(bag.getNum()-1);
        	seedBagDAO.save(bag);
        	message.setCode(0);
            message.setMsg("成功播种"+seed.getSeedName());
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("种植失败");
        }
        return message;
	}

	@Override
	public Message receive(GroundCrop ground) {
		Message message = new Message();
		if(ground.getsStatus() < 5) {
			message.setCode(0);
            message.setMsg("当前作物尚未成熟，再等等吧！");
		}else {
			try {
				Seed seed = seedDAO.findBySeedId(ground.getsId());
				//更新用户信息
				User user = (User) request.getSession().getAttribute("user");
				user.setExp(user.getExp()+seed.getExperience());
				user.setCoins(user.getCoins()+ground.getTrueHarvestNum()*seed.getEachPrice());
				user.setPoints(user.getPoints()+seed.getCredit());
				userDAO.save(user);
				request.getSession().setAttribute("user", user);
				
				String returnMsg = seed.getSeedName()+"收获成功<br>经验：+"+seed.getExperience()+
	            		"<br>金币：+"+ground.getTrueHarvestNum()+"果实*"+seed.getEachPrice()+"金币"+
	            		"<br>积分：+"+seed.getCredit();
				message.setMsg(returnMsg);
				//更新土地信息
				//如果季数已经达到上限，则变为枯草，否则重新生长
				if(ground.getNowSeason() >= seed.getSeasons()) {
					ground.setsStatus(9);
					ground.setPlantTime(0);
					ground.setNowSeason(0);
					ground.setHasInsect(0);
					ground.setTrueHarvestNum(0);
				}else {
					ground.setsStatus(0);
					ground.setHasInsect(0);
		        	ground.setPlantTime(System.currentTimeMillis());
					ground.setNowSeason(ground.getNowSeason()+1);
					ground.setTrueHarvestNum(seed.getReceiveNum());
				}
				groundCropDAO.save(ground);
	        	
	        	return message;
	          
	        }catch(Exception e) {
	        	message.setCode(-1);
	        	message.setMsg("收获失败");
	        	return message;
	        }
		}
		message.setCode(-1);
		message.setMsg("收货失败");
		return message;
		
	}

	@Override
	public Message killWorm(GroundCrop ground) {
		Message message = new Message();
        try {
			User user = (User) request.getSession().getAttribute("user");
			user.setExp(user.getExp()+2);
			user.setCoins(user.getCoins()+1);
			user.setPoints(user.getPoints()+2);
			userDAO.save(user);
			request.getSession().setAttribute("user", user);
        	ground.setHasInsect(0);
        	groundCropDAO.save(ground);
            message.setCode(0);
            message.setMsg("杀虫成功<br>经验：+2<br>金币：+1<br>积分：+2");
        }catch(Exception e) {
            message.setCode(-1);
            message.setMsg("杀虫失败");
        }
        return message;
	}

	@Override
	public Message cleanLand(GroundCrop ground) {
		Message message = new Message();
        try {
        	User user = (User) request.getSession().getAttribute("user");
        	user.setExp(user.getExp()+5);
        	user.setPoints(user.getPoints()+5);
			userDAO.save(user);
			request.getSession().setAttribute("user", user);
        	ground.setsId(0);
        	ground.setsStatus(-1);
        	ground.setGroundStatus(0);
        	groundCropDAO.save(ground);
            message.setCode(0);
            message.setMsg("清理土地成功<br>经验：+5<br>积分：+5");
        }catch(Exception e) {
            message.setCode(-1);
            message.setMsg("清理失败");
        }
        return message;
	}

}
