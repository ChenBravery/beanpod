
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * RoleMenu瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu extends EntityBase implements Serializable {
    
    private String id;
    
    private String roleId;
    
    private String menuId;
    

}
