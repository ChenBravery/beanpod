package org.beanpod.user.service.impl;

import java.util.List;

import org.beanpod.common.msg.PageParam;
import org.beanpod.user.entity.User;
import org.beanpod.user.jpa.IUserDao;
import org.beanpod.user.msg.ServiceResponseMsg;
import org.beanpod.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	
	public ServiceResponseMsg<User> getUserList ( User param, PageRequest pageReq ) {
		ServiceResponseMsg<User> resp = new ServiceResponseMsg<User>();
		
		User user = param;
		if(user == null){
			user = new User();
		}
		
		Example<User> example = Example.of(user);
		
		Page<User> page = userDao.findAll(example, pageReq);
		resp.setPage(page);
		
        log.info("getUserList, resp:" + resp.toString());
        return resp;
    }
	
	public ServiceResponseMsg<User> getUser ( String id ){
		ServiceResponseMsg<User> resp = new ServiceResponseMsg<User>();
		
		User user = userDao.findOne(id);
		if(user == null){
			user = new User();
			user.setId("134524");
			user.setName("test");
			user.setDeleted(0);
			user.setPassword("11452563");
		}
		resp.setData(user);
		
		return resp;
	}
}
