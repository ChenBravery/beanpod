package org.beanpod.auth.msg.user;

import java.util.List;

import org.beanpod.auth.entity.User;
import org.beanpod.common.msg.ResponseMsg;

import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserResp extends ResponseMsg {
	private User data;
}