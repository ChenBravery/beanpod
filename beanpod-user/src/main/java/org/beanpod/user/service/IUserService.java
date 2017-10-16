package org.beanpod.user.service;

import org.beanpod.user.entity.User;
import org.beanpod.user.msg.ServiceResponseMsg;
import org.springframework.data.domain.PageRequest;

public interface IUserService {
	public ServiceResponseMsg<User> getUserList ( User param, PageRequest pageReq);
	public ServiceResponseMsg<User> getUser ( String id );
}
