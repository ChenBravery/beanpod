
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * O2user瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class O2user extends EntityBase implements Serializable {
    
    private String id;
    
	/**
     * 鐢ㄦ埛鍞竴鏍囪瘑
	 */
    private String userId;
    
	/**
     * 鏉ユ簮绫诲瀷: qq, weixin, alipay, weibo
	 */
    private String type;
    
	/**
     * 澶栭儴鐢ㄦ埛鏍囪瘑
	 */
    private String euid;
    
    private String accessToken;
    
    private String refreshToken;
    
	/**
     * 浠ょ墝杩囨湡鏃堕棿
	 */
    private Integer tokenExpiredTime;
    
    private java.util.Date createTime;
    

}
