
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * MenuPermission瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermission extends EntityBase implements Serializable {
    
    private String id;
    
    private String menuId;
    
    private String permissionId;
    

}
