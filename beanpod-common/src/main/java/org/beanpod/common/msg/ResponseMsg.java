package org.beanpod.common.msg;

import lombok.Data;

@Data
public class ResponseMsg {
	private int code;
	private String msg;
}
