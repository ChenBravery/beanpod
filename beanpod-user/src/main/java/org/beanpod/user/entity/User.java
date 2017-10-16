
package org.beanpod.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * User瀹炰綋绫�
 * 
 **/
@Entity
@Table(name="t_sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
	/**
     * 鐢ㄦ埛鍞竴琛ㄧず
	 */
	@Id
	@Column(nullable = false)
    private String id;
    
	/**
     * 鐢ㄦ埛鍚嶇О
	 */
	@Column(nullable = false)
    private String name;
    
	@Column(nullable = false)
    private String password;
    
    @Column(name="creator_id")
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
    @Column(nullable = false)
    private Integer deleted;
    

}
