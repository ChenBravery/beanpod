
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * TokenPermission瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPermission extends EntityBase implements Serializable {
    
    private String id;
    
    private String tokenId;
    
    private String permissionId;
    

}
