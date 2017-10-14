package org.beanpod.auth.service;

import org.beanpod.auth.msg.user.UserListReq;
import org.beanpod.auth.msg.user.UserListResp;
import org.beanpod.auth.msg.user.UserResp;

public interface IUserService {

	public UserListResp getUserList ( UserListReq req );
	public UserResp getUser ( String id );
}
