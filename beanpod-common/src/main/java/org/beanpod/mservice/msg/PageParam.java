package org.beanpod.mservice.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageParam {
    private int pageNum = 1;  // 1 to N
    private int pageSize = 10; // default 10
    
    public PageParam(){
    	pageNum = 1;
    	pageNum = 10;
    }
}
