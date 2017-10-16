package org.beanpod.user.msg;

import org.beanpod.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {

	int page;
	
	int size;
	
}
