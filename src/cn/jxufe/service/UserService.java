package cn.jxufe.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;

public interface UserService {
	 EasyUIData<?> listPage(String username,EasyUIDataPageRequest easyUIDataPageRequest);
	 Message save(User user);
	 Message delete(User user);
	 List<User> findAll();
	 Message buySeed(int seedId,HttpSession session);
	 Page<SeedBag> findMySeeds(HttpSession session, int pageNum);
}
