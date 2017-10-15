package org.beanpod.auth.controller;

import org.beanpod.auth.msg.user.UserListReq;
import org.beanpod.auth.msg.user.UserListResp;
import org.beanpod.auth.msg.user.UserResp;
import org.beanpod.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@RestController
@Log
@RequestMapping(value = "/api")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value="getUserList")
	@GetMapping(value = "/user", consumes = {"application/json; charset=UTF-8"}, produces = "application/json; charset=UTF-8")
	@ResponseBody
    public UserListResp getUserList( @RequestBody UserListReq req ) {
		UserListResp resp = new UserListResp();
		
		resp = userService.getUserList(req);
		
        log.info("/user, resp:" + resp.toString());
        return resp;
    }
	
	@ApiOperation(value="getUser")
	@GetMapping(value = "/user/{id}", produces = "application/json; charset=UTF-8")
	@ResponseBody
    public UserResp getUser( @PathVariable(value="id") String id ) {
		UserResp resp = new UserResp();
		
		resp = userService.getUser(id);
		
        log.info("getUser, resp:" + resp.toString());
        return resp;
    }
	

}
