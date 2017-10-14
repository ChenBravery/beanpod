package org.beanpod.auth.msg.user;

import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import org.beanpod.auth.entity.User;
import org.beanpod.mservice.msg.ResponseMsg;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserListResp extends ResponseMsg {
	private List<User> data;
	private PageInfo<User> pageInfo;
}
