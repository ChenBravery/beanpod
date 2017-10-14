
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * User瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends EntityBase implements Serializable {
    
	/**
     * 鐢ㄦ埛鍞竴琛ㄧず
	 */
    private String id;
    
	/**
     * 鐢ㄦ埛鍚嶇О
	 */
    private String name;
    
    private String password;
    
    private String creatorId;
    
    private String parentId;
    
    private java.util.Date createTime;
    
	/**
     * 鐢ㄦ埛鏇存柊鏃堕棿
	 */
    private java.util.Date updateTime;
    
	/**
     * 鍒犻櫎鏍囪
	 */
    private Integer deleted;
    

}
