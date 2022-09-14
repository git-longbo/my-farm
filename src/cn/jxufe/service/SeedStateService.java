package cn.jxufe.service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedState;

public interface SeedStateService {
	EasyUIData<?> listPage(int seedId,EasyUIDataPageRequest easyUIDataPageRequest);
	SeedState getStateBySeedIdandGrowState(int seedId,int growState);
	Message delete(SeedState seedState);
	Message save(SeedState seedState);
	SeedState findBySIdAndStaus(int seedId, int sStatus);
}
