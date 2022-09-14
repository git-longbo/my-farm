package cn.jxufe.imp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
@Service
public class FarmServiceImpl implements FarmService{
	@Autowired
	private UserDAO userDAO;
	@Override
	public Message setCurUser(HttpSession session,User user) {
		User trueUser = null;
		Message message = new Message();
		try {
		trueUser = userDAO.findByNicheng(user.getNicheng());
		session.setAttribute("user", trueUser);
		message.setCode(0);
		message.setMsg("��ǰ�û��Ѿ��趨Ϊ:"+trueUser.getNicheng()+'['+trueUser.getUsername()+']');
		}catch (Exception e) {
			e.printStackTrace();
			message.setCode(-1);
			message.setMsg("�û��趨ʧ��");
		}
		return message;
	}

}
