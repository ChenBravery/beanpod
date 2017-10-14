
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * AccessToken瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken extends EntityBase implements Serializable {
    
	/**
     * 浠ょ墝鍞竴鏍囪瘑
	 */
    private String id;
    
    private Integer type;
    
	/**
     * 鎺堟潈鐢ㄦ埛
	 */
    private String userId;
    
	/**
     * 搴旂敤ID
	 */
    private String appId;
    
	/**
     * 璁块棶浠ょ墝
	 */
    private String accessToken;
    
	/**
     * 鍒锋柊浠ょ墝
	 */
    private String refreshToken;
    
	/**
     * 鍒涘缓鏃堕棿
	 */
    private java.util.Date createTime;
    
    private java.util.Date refreshTime;
    
	/**
     * 璁块棶浠ょ墝杩囨湡鏃堕棿
	 */
    private java.util.Date expiredTime;
    
	/**
     * 鍒锋柊浠ょ墝杩囨湡鏃堕棿
	 */
    private java.util.Date refreshExpiredTime;
    
    private Integer available;
    
    private Integer deteted;
    

}
