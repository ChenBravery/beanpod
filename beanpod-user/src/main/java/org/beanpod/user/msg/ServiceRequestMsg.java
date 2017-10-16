package org.beanpod.user.msg;

import org.beanpod.user.entity.User;

import lombok.Data;

@Data
public class ServiceRequestMsg<DataT> {

	User param;
	PageReq page;
	
	public ServiceRequestMsg() {
		// TODO Auto-generated constructor stub
	}

}
