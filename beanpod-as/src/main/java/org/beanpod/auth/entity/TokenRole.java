
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * TokenRole瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRole extends EntityBase implements Serializable {
    
    private String id;
    
    private String tokenId;
    
    private String roleId;
    

}
