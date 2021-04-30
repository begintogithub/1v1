package com.xiaoxiongwang.service;

import com.xiaoxiongwang.domain.Room;

public interface WebSocketService {
	
	/**
	 * 获取已使用的房间列表
	 */
	void getUseRoomList();
	

	void singleRoomSend(Room room);
	
	
	void stopRoomSend(int roomId);
	
}
