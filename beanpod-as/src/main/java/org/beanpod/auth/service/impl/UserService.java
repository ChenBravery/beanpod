package org.beanpod.auth.service.impl;

import java.util.List;

import org.beanpod.auth.entity.User;
import org.beanpod.auth.dao.mapper.IUserDao;
import org.beanpod.auth.msg.user.UserListReq;
import org.beanpod.auth.msg.user.UserListResp;
import org.beanpod.auth.msg.user.UserResp;
import org.beanpod.auth.service.IUserService;
import org.beanpod.common.msg.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.java.Log;

@Service
@Log
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	
	public UserListResp getUserList ( UserListReq req ) {
		UserListResp resp = new UserListResp();
		
		User user = req.getParam();
		if(user == null){
			user = new User();
		}
		PageParam pageParam = req.getPageParam();
		
		List<User> userList = null;
		
		if(pageParam == null) {
			//pageParam = new PageParam(1, 10);
			pageParam = new PageParam();
		}else{
			if(pageParam.getPageNum() <= 0){
				pageParam.setPageNum(1);
			}
			if(pageParam.getPageSize() <= 0){
				pageParam.setPageSize(10);
			}
		}
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		userList = userDao.selectList(user);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		
		resp.setPageInfo(pageInfo);
		resp.setData(userList);
		
        log.info("getUserList, resp:" + resp.toString());
        return resp;
    }
	
	public UserResp getUser ( String id ){
		UserResp resp = new UserResp();
		
		User user = userDao.selectById(id);
		resp.setData(user);
		
		return resp;
	}
}
