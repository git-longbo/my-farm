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
    /* ����webSocket�Ựʱ���ڻỰ�����б���ע���webSocket�Ự  */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        users.add(session);  
        user = (User) session.getHandshakeAttributes().get("user");
        LOGGER.info("�û� " + user.getUsername()+ " �ɹ���������");
    }  
    
    
    @Override 
    /* ����webSocket�Ự�����ر��¼�ʱ������Ự�����б�  */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {   
    	User user = (User) session.getHandshakeAttributes().get("user");
        LOGGER.info("�û� " + user.getUsername()+ "�ţ� ���ӹرա�״̬: " + status); 
        users.remove(session);  
    }  
      
    @Override
    /* �����������ʱ�رո��û���webSocket�Ự��������Ự�����б�  */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    	User user = (User) session.getHandshakeAttributes().get("user");
        if (session.isOpen()) {  
            session.close();  
        }  
        LOGGER.debug("�û�: " + user.getUsername()  + " �������ڴ�����󱻹ر�......");  
        users.remove(session);
    }
    
    /* ��ȫ���û�����Ϣ  */
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
    
    /* ��һ�û�����Ϣ  */
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
    
    //�����������״̬
	public void checkCropStatus() {
		
		List<GroundCrop> groundcropCrops = groundCropDAO.findAll();
		boolean isChange = false;
		//������������
		for(int i=0;i<groundcropCrops.size();i++) {
			isChange = false;
			GroundCrop ground = groundcropCrops.get(i);
			//�������û��ֲ������Ѿ���ή�����߳��죬�������һ���ж�
			if(ground.getGroundStatus()==0 || ground.getsStatus() == 9 || ground.getsStatus() == 5) {
				continue;
			}
			//��ȡ���ӵ�ǰ�׶�����������Ϣ
		//	SeedState state = seedStateDAO.findBySeedIdAndGrowState(ground.getsId(),ground.getsStatus());
			//��С�ڵ�ǰ����׶ε�ȡ��������������ʱ��
		
			//�������
			SeedState state = seedStateDAO.findBySeedIdAndGrowState(ground.getsId(),ground.getsStatus());
			if(ground.getHasInsect() == 0 && Math.random() < state.getPest()) {
				ground.setHasInsect(1);
				isChange = true;
			}
			//�ж��Ƿ�����       �����ǰʱ�����ڸ������׶�����ʱ�䲢�ҵ�ǰ״̬��ɳ�״̬��������ı䵱ǰ����ֲ��״̬
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
				//�������ʱֲ���г��ӣ�����ֲ���ʵ����1~2��
				if(nowStatus == 5 && ground.getHasInsect() == 1) {
					ground.setTrueHarvestNum(ground.getTrueHarvestNum() - Math.random()<0.5?1:2);
				}
				ground.setsStatus(nowStatus+1);
				isChange = true;
			}
			// �����ǰ���ؽ�����״̬�仯,����������Ϣ
			if(isChange) {
				groundCropDAO.save(ground);
			}
			
		}
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					User farmUser = (User) user.getHandshakeAttributes().get("user");
					//��ȡ��ǰ�û���������״̬
					List<GroundCrop> grounds = groundCropDAO.findByUId(farmUser.getId());
					user.sendMessage(new TextMessage(JSONArray.fromObject(grounds).toString()));  
				
			    }
			} catch (IOException e) {  
				e.printStackTrace();  
			} 
		}
		System.out.println("2��ˢ��------------------------");
	} 
}
