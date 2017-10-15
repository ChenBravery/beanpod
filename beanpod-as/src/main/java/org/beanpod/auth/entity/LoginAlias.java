
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * LoginAlias瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAlias extends EntityBase implements Serializable {
    
    private String id;
    
	/**
     * 鐢ㄦ埛鍞竴鏍囪瘑
	 */
    private String userId;
    
	/**
     * 鐧诲綍鍚嶇被鍨�
            0 account_name 甯愭埛鍚嶇О
            1 Email 閭欢鍦板潃
            2 mobile 鎵嬫満鍙�
            
            
	 */
    private Integer loginType;
    
	/**
     * 鐧诲綍鍚嶇О
	 */
    private String loginName;
    
	/**
     * 纭鏍囧織 0 楠岃瘉 1 宸查獙璇�
	 */
    private Integer verified;
    
	/**
     * 鐧诲綍鍚嶅垱寤烘椂闂�
	 */
    private java.util.Date createTime;
    

}
