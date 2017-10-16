package org.beanpod.user.msg;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import lombok.Data;

@Data
public class ServiceResponseMsg<DataT> {

	private int code;
	private String msg;
	private DataT data;
	Page<DataT> page;
	PageRequest pageReq;
		
	public ServiceResponseMsg() {
	}

}
