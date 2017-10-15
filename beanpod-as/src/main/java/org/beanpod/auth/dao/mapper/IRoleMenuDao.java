
package org.beanpod.auth.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import org.beanpod.auth.entity.RoleMenu;

/**
 * 
 * RoleMenu鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IRoleMenuDao {

	@Results(id="roleMenu",value={
		@Result(column="id", property="id"),
		@Result(column="role_id", property="roleId"),
		@Result(column="menu_id", property="menuId")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞RoleMenu
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( RoleMenu roleMenu );

	/**
	 * 
	 * 鎵归噺娣诲姞RoleMenu
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<RoleMenu> roleMenuList );
    
	/**
	 * 
	 * 鍒犻櫎RoleMenu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎RoleMenu
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎RoleMenu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼RoleMenu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( RoleMenu roleMenu );

	/**
	 * 
	 * 鎵归噺淇敼RoleMenu
	 * 
	 **/
	//int batchUpdate( @Param("roleMenu")RoleMenu roleMenu, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨRoleMenu
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	RoleMenu selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨRoleMenu鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<RoleMenu> selectList ( RoleMenu roleMenu );
    
	/**
	 * 
	 * 鏌ヨRoleMenu鍒楄〃
	 * 
	 **/
	//List<RoleMenu> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨRoleMenu鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( RoleMenu roleMenu );
    
	/**
	 * 
	 * 鏌ヨRoleMenu鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(RoleMenu roleMenu) {
			return new SQL(){{
				INSERT_INTO("t_sys_role_menu");
				if ( roleMenu.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( roleMenu.getRoleId() == null) {
					VALUES("role_id", "NULL");
				} else {
					VALUES("role_id", "#{roleId}");
				}
				if ( roleMenu.getMenuId() == null) {
					VALUES("menu_id", "NULL");
				} else {
					VALUES("menu_id", "#{menuId}");
				}
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<RoleMenu>> map ) {
			boolean genId = false;
	        List<RoleMenu> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_role_menu (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("role_id, ");
			sb.append("menu_id");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].roleId}, ");
			patternSb.append("#'{'list[{0}].menuId}");
			patternSb.append(")");
	        // MessageFormat mf = new MessageFormat("(#'{'list[{0}].id}, #'{'list[{0}].name}, #'{'list[{0}].password}, #'{'list[{0}].creatorId}, #'{'list[{0}].parentId}, SYSDATE(), SYSDATE(), 0)");
			MessageFormat mf = new MessageFormat(patternSb.toString());
	        for (int i = 0; i < list.size(); i++) {
				if(genId){
					String uuid = UUID.randomUUID().toString().replace("-", "");
					list.get(i).setId(uuid);
				}
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < list.size() - 1) {  
	                sb.append(",");
	            }  
	        }  
	        String sql = sb.toString();
	        return sql;
	    }
		
		public String deleteById(String id) {
			return new SQL(){{
				UPDATE("t_sys_role_menu");
				SET("deleted = 1");
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String deleteAllByIds(Map<String, List<String>> map) {
			List<String> ids = map.get("list");
			StringBuilder sb = new StringBuilder();
			MessageFormat mf = new MessageFormat("#'{'list[{0}]}");
			sb.append("id in (");
			int count = ids.size();
	        for (int i = 0; i < count; i++) {
	            sb.append(mf.format(new Object[]{i}));  
	            if (i < count - 1) {  
	                sb.append(",");
	            }
	        }
			sb.append(')');
			
			return new SQL(){{
				UPDATE("t_sys_role_menu");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( RoleMenu roleMenu ) {
			return new SQL(){{
				UPDATE("t_sys_role_menu");
				if ( roleMenu.getId() != null) {
					SET("id = #{id}");
				}
				if ( roleMenu.getRoleId() != null) {
					SET("role_id = #{roleId}");
				}
				if ( roleMenu.getMenuId() != null) {
					SET("menu_id = #{menuId}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_role_menu");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( RoleMenu roleMenu ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_role_menu");
				if ( roleMenu.getId() != null && !roleMenu.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( roleMenu.getRoleId() != null && !roleMenu.getRoleId().isEmpty() ) {
					WHERE("role_id = #{roleId}");
				}
				if ( roleMenu.getMenuId() != null && !roleMenu.getMenuId().isEmpty() ) {
					WHERE("menu_id = #{menuId}");
				}
			}}.toString();
		}
	}	
}
