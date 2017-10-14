package org.beanpod.mservice.msg;

import lombok.Data;

@Data
public class ResponseMsg {
	private int code;
	private String msg;
}
