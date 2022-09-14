package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private SeedDAO seedDAO;
	@Autowired
	private SeedBagDAO seedBagDAO;
	@Override
	public EasyUIData<?> listPage(String username, EasyUIDataPageRequest easyUIDataPageRequest) {
		EasyUIData<User> result = new EasyUIData<User>();
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
	    if(easyUIDataPageRequest.getOrder().equals("asc")) {
	        orders.add(new Sort.Order(Direction.ASC,easyUIDataPageRequest.getSort()));
	    }else {
	        orders.add(new Sort.Order(Direction.DESC,easyUIDataPageRequest.getSort()));
	    }
	    Pageable pageRequest =new PageRequest(easyUIDataPageRequest.getPage()-1,
			easyUIDataPageRequest.getRows(), 
			new Sort(orders));     
	    username = '%'+username+'%';
	    Page<User> curPage = userDAO.findByUsernameLike(username,pageRequest);
	    result.setRows(curPage.getContent());
	    result.setTotal(curPage.getTotalElements());
	    return result;
	}

	@Override
	public Message save(User user) {
		Message message = new Message();
		try {
		userDAO.save(user);
		message.setCode(0);
		message.setMsg("用户保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("用户保存失败");
		}
		return message;
	}

	@Override
	public Message delete(User user) {
		Message message = new Message();
		try {
		userDAO.delete(user);
		message.setCode(0);
		message.setMsg("用户删除成功");
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("用户删除失败");
		}
		return message;
	}
	@Override
	public List<User> findAll(){
		List<User> users = null;
		try {
			users = userDAO.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	@Transactional
	public Message buySeed(int seedId, HttpSession session) {
		
		Message message = new Message();
		//购买时判断用户是否登录
		User user =(User) session.getAttribute("user");
		Seed seedMesg = new Seed();
		seedMesg = seedDAO.findBySeedId(seedId);
		if(user != null) {
			//查看当前用户是否有种子数据
			String username = user.getUsername();
			SeedBag seedBag = seedBagDAO.findByUsernameAndSeedId(username,seedId);		
			if(seedBag == null) {
				seedBag = new SeedBag();
				seedBag.setUsername(username);
				seedBag.setSeedId(seedId);
				seedBag.setNum(1);		
			}else {
				seedBag.setNum(seedBag.getNum()+1);
				
			}
			//购买之后消费金币
			int coins = user.getCoins() - seedMesg.getPrice();
			if(coins >= 0) {
				user.setCoins(coins);
				userDAO.save(user);
				seedBagDAO.save(seedBag);
				message.setCode(1);
				message.setMsg("种子购买成功");
			}else {
				message.setCode(-1);
				message.setMsg("金币不足，购买失败");
			}
		}else {
			message.setCode(-1);
			message.setMsg("用户未登录，无法购买种子");
		}
		return message;
	}

	@Override
	public Page<SeedBag> findMySeeds(HttpSession session, int pageNum) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
		Pageable page = new PageRequest(pageNum, 4);
        Page<SeedBag> bag = seedBagDAO.findByUsername(user.getUsername(),page);
		return bag;
		}else {
			return null;
		}
	}

}
