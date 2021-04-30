package com.xiaoxiaowang.service;

import net.sf.json.JSONObject;

public interface PushMsgService {

	
	public JSONObject getPushMsgList(int page);
	
	/**
	 * 全服推送
	 * @param push_msg
	 */
	public void addWholeServicePush(Integer t_user_role,String push_msg);
	
}
