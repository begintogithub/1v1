package com.xiaoxiongwang.evnet;

import org.springframework.context.ApplicationEvent;

import com.xiaoxiongwang.domain.Room;

public class PushLinkUser extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PushLinkUser(Room source) {
		super(source);
	}

}
