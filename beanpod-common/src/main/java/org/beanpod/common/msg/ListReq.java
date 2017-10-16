package org.beanpod.common.msg;

import org.beanpod.common.msg.PageParam;

import lombok.Data;

@Data
public class ListReq<T> {
	private T param;
    private PageParam pageParam;
}
