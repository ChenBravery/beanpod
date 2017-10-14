
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * Permission瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends EntityBase implements Serializable {
    
    private String id;
    
	/**
     * 鍚嶇О
	 */
    private String name;
    
	/**
     * 鏄剧ず鍚嶇О
	 */
    private String displayName;
    
	/**
     * 鏉冮檺绫诲瀷
	 */
    private Integer type;
    
	/**
     * 鏄惁绯荤粺鏉冮檺
	 */
    private Integer isSys;
    
	/**
     * 鏄惁鍙敤
	 */
    private Integer usable;
    
	/**
     * 鐖舵潈闄�
	 */
    private String parentId;
    
    private String permission;
    
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
