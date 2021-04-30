package com.xiaoxiongwang.evnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.xiaoxiongwang.domain.MessageEntity;
import com.xiaoxiongwang.service.MessageService;
import com.xiaoxiongwang.util.PushUtil;

@Component("PushMesgHandle")
public class PushMesgHandle implements ApplicationListener<PushMesgEvnet> {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MessageService messageService;

	public void onApplicationEvent(PushMesgEvnet event) {
		// TODO Auto-generated method stub
		MessageEntity me = (MessageEntity) event.getSource();
		logger.info("进入了异步通知!");
		if (null != me) {
			messageService.pushMessage(me);
			if (me.getPushType() != 6)
				// 发起推送
				PushUtil.sendPush(me.getT_user_id(), me.getT_message_content());
			else
				PushUtil.sendTestPush(me.getT_user_id(), me.getT_message_content(),me.getRoomId(),me.getConnectUserId(),me.getSatisfy());
		}
	}

}
