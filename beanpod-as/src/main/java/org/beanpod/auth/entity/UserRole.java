
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * UserRole瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends EntityBase implements Serializable {
    
    private String id;
    
    private String userId;
    
    private String roleId;
    

}
