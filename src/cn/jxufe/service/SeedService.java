package cn.jxufe.service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.Seed;

public interface SeedService {
	
	 EasyUIData<?> listPage(String seedName,EasyUIDataPageRequest easyUIDataPageRequest);
	 Message save(Seed seed);
	 Message delete(Seed seed);
}
