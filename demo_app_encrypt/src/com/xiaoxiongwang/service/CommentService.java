package com.xiaoxiongwang.service;

import com.xiaoxiongwang.util.MessageUtil;

public interface CommentService {
	
	
	/**
	 * 对主播进行评论
	 * @param commUserId
	 * @param coverCommUserId
	 * @param commScore
	 * @param lables
	 * @return
	 */
	public MessageUtil saveComment(int commUserId,int coverCommUserId,int commScore,String comment,String lables);

}
