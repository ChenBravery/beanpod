package org.beanpod.mservice.msg;

import org.beanpod.mservice.msg.PageParam;

import lombok.Data;

@Data
public class ListReq<T> {
	private T param;
    private PageParam pageParam;
}
