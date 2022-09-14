package cn.jxufe.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.jxufe.dao.GroundCropDAO;
import cn.jxufe.dao.SeedStateDAO;
import cn.jxufe.entity.GroundCrop;
import cn.jxufe.entity.SeedState;
import cn.jxufe.entity.User;
import net.sf.json.JSONArray;

public class FarmActionHandler extends TextWebSocketHandler{
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);  	  
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();  
    private User user;
    @Autowired
    private GroundCropDAO groundCropDAO;
    
    @Autowired
    private SeedStateDAO seedStateDAO;
    
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    } 	
	
    @Override  
    /* 发布webSocket会话时，在会话管理列表中注册该webSocket会话  */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        users.add(session);  
        user = (User) session.getHandshakeAttributes().get("user");
        LOGGER.info("用户 " + user.getUsername()+ " 成功建立连接");
    }  
    
    
    @Override 
    /* 发生webSocket会话主动关闭事件时，清理会话管理列表  */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {   
    	User user = (User) session.getHandshakeAttributes().get("user");
        LOGGER.info("用户 " + user.getUsername()+ "号， 连接关闭。状态: " + status); 
        users.remove(session);  
    }  
      
    @Override
    /* 发生传输错误时关闭该用户的webSocket会话，并清理会话管理列表  */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    	User user = (User) session.getHandshakeAttributes().get("user");
        if (session.isOpen()) {  
            session.close();  
        }  
        LOGGER.debug("用户: " + user.getUsername()  + " 连接由于传输错误被关闭......");  
        users.remove(session);
    }
    
    /* 向全部用户发消息  */
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
    }
    
    /* 向单一用户发消息  */
    public void sendMessageToUser(int userId, TextMessage message) {
    	for (WebSocketSession user : users) {
    		User chatUser = (User) user.getHandshakeAttributes().get("user");
        	if(chatUser==null)continue;
            if (chatUser.getId() == userId) {  
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    }
    
    //监管所有土地状态
	public void checkCropStatus() {
		
		List<GroundCrop> groundcropCrops = groundCropDAO.findAll();
		boolean isChange = false;
		//监听所有土地
		for(int i=0;i<groundcropCrops.size();i++) {
			isChange = false;
			GroundCrop ground = groundcropCrops.get(i);
			//如果土地没有植物，或者已经枯萎，或者成熟，则进行下一个判断
			if(ground.getGroundStatus()==0 || ground.getsStatus() == 9 || ground.getsStatus() == 5) {
				continue;
			}
			//获取种子当前阶段生长所需信息
		//	SeedState state = seedStateDAO.findBySeedIdAndGrowState(ground.getsId(),ground.getsStatus());
			//把小于当前作物阶段的取出来，计算生长时间
		
			//生虫概率
			SeedState state = seedStateDAO.findBySeedIdAndGrowState(ground.getsId(),ground.getsStatus());
			if(ground.getHasInsect() == 0 && Math.random() < state.getPest()) {
				ground.setHasInsect(1);
				isChange = true;
			}
			//判断是否生长       如果当前时间差大于该生长阶段所需时间并且当前状态与成长状态不符，则改变当前土地植物状态
			int needTime = 0;
			int flag = ground.getsStatus();
			while(flag>=0) {
				SeedState state2 =  seedStateDAO.findBySeedIdAndGrowState(ground.getsId(),flag);
				needTime += state2.getStateTime();
				flag--;
			}
			long nowTime = System.currentTimeMillis();
			long timeDifference = (nowTime - ground.getPlantTime())/1000;
			if(timeDifference >= needTime) {
				int nowStatus = ground.getsStatus();
				//如果成熟时植物有虫子，则让植物果实减少1~2个
				if(nowStatus == 5 && ground.getHasInsect() == 1) {
					ground.setTrueHarvestNum(ground.getTrueHarvestNum() - Math.random()<0.5?1:2);
				}
				ground.setsStatus(nowStatus+1);
				isChange = true;
			}
			// 如果当前土地进行了状态变化,则保留土地信息
			if(isChange) {
				groundCropDAO.save(ground);
			}
			
		}
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					User farmUser = (User) user.getHandshakeAttributes().get("user");
					//获取当前用户所有土地状态
					List<GroundCrop> grounds = groundCropDAO.findByUId(farmUser.getId());
					user.sendMessage(new TextMessage(JSONArray.fromObject(grounds).toString()));  
				
			    }
			} catch (IOException e) {  
				e.printStackTrace();  
			} 
		}
		System.out.println("2秒刷新------------------------");
	} 
}
