
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * Menu瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends EntityBase implements Serializable {
    
	/**
     * 鑿滃崟鍞竴鏍囪瘑
	 */
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
     * 鑿滃崟绫诲瀷
	 */
    private Integer type;
    
	/**
     * 鏄惁绯荤粺鑿滃崟
	 */
    private Integer isSys;
    
	/**
     * 鏄惁鍙敤
	 */
    private Integer usable;
    
	/**
     * 鏄惁鏄剧ず
	 */
    private Integer show;
    
	/**
     * 鐖舵潈闄�
	 */
    private String parentId;
    
	/**
     * 鑿滃崟灞傜骇 0,1,2,3,...
	 */
    private Integer level;
    
	/**
     * 鏄剧ず椤哄簭鍙�
	 */
    private Integer orderNumber;
    
    private String href;
    
    private String target;
    
    private String icon;
    
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
