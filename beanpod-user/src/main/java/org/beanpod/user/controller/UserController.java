package org.beanpod.user.controller;

import org.beanpod.user.entity.User;
import org.beanpod.user.msg.ServiceRequestMsg;
import org.beanpod.user.msg.ServiceResponseMsg;
import org.beanpod.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@ApiOperation(value = "getUserList")
	@GetMapping(value = "/user", consumes = {
			"application/x-www-form-urlencoded; charset=UTF-8" }, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ServiceResponseMsg<User> getUserList(@RequestParam ServiceRequestMsg<User> req) {
		ServiceResponseMsg<User> resp = null;

		resp = userService.getUserList(req.getParam(), new PageRequest(req.getPage().getPage(), req.getPage().getSize()));

		log.info("/user, resp:" + resp.toString());
		return resp;
	}

	@ApiOperation(value = "getUser")
	@GetMapping(value = "/user/{id}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ServiceResponseMsg<User> getUser(@PathVariable(value = "id") String id) {
		ServiceResponseMsg<User> resp = null;

		resp = userService.getUser(id);

		log.info("getUser, resp:" + resp.toString());
		return resp;
	}

}
