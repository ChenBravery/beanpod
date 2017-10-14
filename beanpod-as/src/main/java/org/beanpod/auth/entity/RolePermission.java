
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * RolePermission瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends EntityBase implements Serializable {
    
    private String id;
    
    private String roleId;
    
    private String permissionId;
    

}
