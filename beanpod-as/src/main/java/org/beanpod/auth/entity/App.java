
package org.beanpod.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 
 * App瀹炰綋绫�
 * 
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class App extends EntityBase implements Serializable {
    
	/**
     * 搴旂敤鍞竴鏍囪瘑
	 */
    private String id;
    
	/**
     * 搴旂敤鎵�灞炵敤鎴稩D
	 */
    private String owerId;
    
    private Integer available;
    
    private Integer deteted;
    

}
