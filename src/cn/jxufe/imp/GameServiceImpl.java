package cn.jxufe.imp;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.service.GameService;
import cn.jxufe.websocket.FarmActionHandler;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	FarmActionHandler farmActionHandler;
	
	Timer timer = new Timer();
	
	@Override
	public void gameStart() {
		timer.schedule(
			new TimerTask() {
				@Override
	            public void run() {
					checkCropStatus();
	            }
	        }, 0,2000);
	}
	

	private void checkCropStatus(){
		farmActionHandler.checkCropStatus();
	}	
}
