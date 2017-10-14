
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * Role瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends EntityBase implements Serializable {
    
    private String id;
    
	/**
     * 瑙掕壊鍚嶇О
	 */
    private String name;
    
	/**
     * 瑙掕壊鏄剧ず鍚嶇О
	 */
    private String displayName;
    
	/**
     * 瑙掕壊绫诲瀷
	 */
    private Integer roleType;
    
	/**
     * 鏄惁绯荤粺瑙掕壊
	 */
    private Integer isSys;
    
	/**
     * 鏄惁鍙敤
	 */
    private Integer usable;
    
	/**
     * 鐖惰鑹�
	 */
    private String parentId;
    
    private String description;
    
	/**
     * 鍒涘缓鑰�
	 */
    private String creatorId;
    
	/**
     * 鍒涘缓鏃堕棿
	 */
    private java.util.Date createTime;
    
	/**
     * 鏇存柊鑰�
	 */
    private String updatorId;
    
	/**
     * 鏇存柊鏃堕棿
	 */
    private java.util.Date updateTime;
    
	/**
     * 鍒犻櫎鏍囪
	 */
    private Integer deleted;
    

}
