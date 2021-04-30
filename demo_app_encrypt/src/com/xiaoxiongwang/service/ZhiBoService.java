package com.xiaoxiongwang.service;

import com.xiaoxiongwang.util.MessageUtil;
import net.sf.json.JSONObject;

public interface ZhiBoService {




	public MessageUtil getLongSetUpList();
	/**
	 * 获取签名
	 * @param phone
	 * @return
	 */
	MessageUtil ZhiBoSig(JSONObject json);

	/**
	 * 获取签名
	 * @param phone
	 * @return
	 */
	MessageUtil ZhiBoCosSig(JSONObject json);

	/**
	 * 更新房间
	 * @param phone
	 * @return
	 */
	MessageUtil ZhiBoUploadRoom(JSONObject json);

	
}
