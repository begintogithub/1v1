package com.xiaoxiongwang.evnet;

import org.springframework.context.ApplicationEvent;

import com.xiaoxiongwang.domain.MessageEntity;

public class PushMesgEvnet extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public PushMesgEvnet(MessageEntity source) {
		super(source);
	}

}
