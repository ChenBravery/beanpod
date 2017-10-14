package org.beanpod.auth.controller;

import java.util.LinkedList;
import java.util.List;

import org.beanpod.auth.entity.User;
import org.beanpod.auth.dao.mapper.IUserDao;
import org.beanpod.auth.msg.user.UserListReq;
import org.beanpod.auth.msg.user.UserListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@RestController
@Log
@RequestMapping(value = "/api")
public class AuthController {
	
	@Autowired
	private IUserDao userDao;
	
	@ApiOperation(value="测试", notes="")
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(@RequestParam String id) {
    	
		User user = new User();
		//user.setId("FJUTFDHJGJH4323567576");
		//user.setId(id);
		user.setName("Test");
		user.setPassword("123456");
		user.setCreatorId(null);
		user.setParentId(null);
		user.setCreateTime(null);
		user.setUpdateTime(null);
		
		userDao.insert(user);
		
		List<User> userList = new LinkedList<User>();
		userList.add(new User(null, "xx1", "222", null, null, null,null, 0));
		userList.add(new User(null, "xx2", "222", null, null, null,null, 0));
		userList.add(new User(null, "xx3", "222", null, null, null,null, 0));
		userDao.insertAll(userList);
		
		User userResult = userDao.selectById(user.getId());
		user = new User();
		user.setName("xx");
		userList = userDao.selectList(user);
		
        log.info("/test, userResult:" + userResult.toString() + "\n\tlist:"+userList.toString());
        return userResult.toString() + "\n"+userList.toString();
    }
	
	@ApiOperation(value="测试", notes="")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete() {
    	
		List<String> ids = new LinkedList<String>();
		ids.add("32a193f2665f11e794ac00247edc8211");
		ids.add("4c3f45966d9b475bb0655cc83817a405");
		ids.add("bb59482fe50d4adbb05368abed9d5816");
		int result = userDao.deleteAllByIds(ids);
		
        log.info("/delete, result:" + String.valueOf(result));
        return "delete " + String.valueOf(result);
    }
	
	@ApiOperation(value="测试", notes="", consumes="application/json" )
    //@RequestMapping(value = "/select", method = RequestMethod.GET)
	@GetMapping(value = "/select", consumes = {"application/json"}, produces = "application/json; charset=UTF-8")
	@ResponseBody
    public UserListResp select( @RequestBody UserListReq req ) {
		UserListResp resp = new UserListResp();
		
		User user = new User();
		
		List<User> userList = new LinkedList<User>();
		
		//PageHelper.startPage(req.getPageNum(), req.getPageSize());
		userList = userDao.selectList(user);
		PageInfo<User> pageInfo = new PageInfo(userList);
		
		resp.setPageInfo(pageInfo);
		resp.setData(userList);
		
        log.info("/select, resp:" + resp.toString());
        return resp;
    }
}
