package com.xiaoxiongwang.evnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xiaoxiongwang.domain.Room;
import com.xiaoxiongwang.service.WebSocketService;

@Component
public class PushLinkUserHandle implements ApplicationListener<PushLinkUser> {

	@Autowired
	private WebSocketService webSocketService;
	
	@Override
	public void onApplicationEvent(PushLinkUser event) {
	   
		Room room = (Room) event.getSource();
		if(null != room) {
			webSocketService.singleRoomSend(room);
		}
	}

}
