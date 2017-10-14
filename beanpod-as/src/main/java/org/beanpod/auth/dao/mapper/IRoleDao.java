
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

import org.beanpod.auth.entity.Role;

/**
 * 
 * Role鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IRoleDao {

	@Results(id="role",value={
		@Result(column="id", property="id"),
		@Result(column="name", property="name"),
		@Result(column="display_name", property="displayName"),
		@Result(column="role_type", property="roleType"),
		@Result(column="is_sys", property="isSys"),
		@Result(column="usable", property="usable"),
		@Result(column="parent_id", property="parentId"),
		@Result(column="description", property="description"),
		@Result(column="creator_id", property="creatorId"),
		@Result(column="create_time", property="createTime"),
		@Result(column="updator_id", property="updatorId"),
		@Result(column="update_time", property="updateTime"),
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞Role
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( Role role );

	/**
	 * 
	 * 鎵归噺娣诲姞Role
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<Role> roleList );
    
	/**
	 * 
	 * 鍒犻櫎Role
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎Role
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎Role
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼Role
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( Role role );

	/**
	 * 
	 * 鎵归噺淇敼Role
	 * 
	 **/
	//int batchUpdate( @Param("role")Role role, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨRole
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	Role selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨRole鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<Role> selectList ( Role role );
    
	/**
	 * 
	 * 鏌ヨRole鍒楄〃
	 * 
	 **/
	//List<Role> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨRole鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( Role role );
    
	/**
	 * 
	 * 鏌ヨRole鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(Role role) {
			return new SQL(){{
				INSERT_INTO("t_sys_role");
				if ( role.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( role.getName() == null) {
					VALUES("name", "NULL");
				} else {
					VALUES("name", "#{name}");
				}
				if ( role.getDisplayName() == null) {
					VALUES("display_name", "NULL");
				} else {
					VALUES("display_name", "#{displayName}");
				}
				if ( role.getRoleType() == null) {
					VALUES("role_type", "NULL");
				} else {
					VALUES("role_type", "#{roleType}");
				}
				if ( role.getIsSys() == null) {
					VALUES("is_sys", "NULL");
				} else {
					VALUES("is_sys", "#{isSys}");
				}
				if ( role.getUsable() == null) {
					VALUES("usable", "NULL");
				} else {
					VALUES("usable", "#{usable}");
				}
				if ( role.getParentId() == null) {
					VALUES("parent_id", "NULL");
				} else {
					VALUES("parent_id", "#{parentId}");
				}
				if ( role.getDescription() == null) {
					VALUES("description", "NULL");
				} else {
					VALUES("description", "#{description}");
				}
				if ( role.getCreatorId() == null) {
					VALUES("creator_id", "NULL");
				} else {
					VALUES("creator_id", "#{creatorId}");
				}
				VALUES("create_time", "SYSDATE()");
				if ( role.getUpdatorId() == null) {
					VALUES("updator_id", "NULL");
				} else {
					VALUES("updator_id", "#{updatorId}");
				}
				VALUES("update_time", "SYSDATE()");
				VALUES("deleted", "0");
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<Role>> map ) {
			boolean genId = false;
	        List<Role> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_role (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("name, ");
			sb.append("display_name, ");
			sb.append("role_type, ");
			sb.append("is_sys, ");
			sb.append("usable, ");
			sb.append("parent_id, ");
			sb.append("description, ");
			sb.append("creator_id, ");
			sb.append("create_time, ");
			sb.append("updator_id, ");
			sb.append("update_time, ");
			sb.append("deleted");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].name}, ");
			patternSb.append("#'{'list[{0}].displayName}, ");
			patternSb.append("#'{'list[{0}].roleType}, ");
			patternSb.append("#'{'list[{0}].isSys}, ");
			patternSb.append("#'{'list[{0}].usable}, ");
			patternSb.append("#'{'list[{0}].parentId}, ");
			patternSb.append("#'{'list[{0}].description}, ");
			patternSb.append("#'{'list[{0}].creatorId}, ");
			patternSb.append("SYSDATE(), ");
			patternSb.append("#'{'list[{0}].updatorId}, ");
			patternSb.append("SYSDATE(), ");
			patternSb.append("0");
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
				UPDATE("t_sys_role");
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
				UPDATE("t_sys_role");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( Role role ) {
			return new SQL(){{
				UPDATE("t_sys_role");
				if ( role.getId() != null) {
					SET("id = #{id}");
				}
				if ( role.getName() != null) {
					SET("name = #{name}");
				}
				if ( role.getDisplayName() != null) {
					SET("display_name = #{displayName}");
				}
				if ( role.getRoleType() != null && role.getRoleType() != -1) {
					SET("role_type = #{roleType}");
				}
				if ( role.getIsSys() != null && role.getIsSys() != -1) {
					SET("is_sys = #{isSys}");
				}
				if ( role.getUsable() != null && role.getUsable() != -1) {
					SET("usable = #{usable}");
				}
				if ( role.getParentId() != null) {
					SET("parent_id = #{parentId}");
				}
				if ( role.getDescription() != null) {
					SET("description = #{description}");
				}
				if ( role.getCreatorId() != null) {
					SET("creator_id = #{creatorId}");
				}
				if ( role.getUpdatorId() != null) {
					SET("updator_id = #{updatorId}");
				}
				SET("update_time = SYSDATE()");
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_role");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( Role role ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_role");
				if ( role.getId() != null && !role.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( role.getName() != null && !role.getName().isEmpty() ) {
					WHERE("name = #{name}");
				}
				if ( role.getDisplayName() != null && !role.getDisplayName().isEmpty() ) {
					WHERE("display_name = #{displayName}");
				}
				if ( role.getRoleType() != null && role.getRoleType() != -1) {
					WHERE("role_type = #{roleType}");
				}
				if ( role.getIsSys() != null && role.getIsSys() != -1) {
					WHERE("is_sys = #{isSys}");
				}
				if ( role.getUsable() != null && role.getUsable() != -1) {
					WHERE("usable = #{usable}");
				}
				if ( role.getParentId() != null && !role.getParentId().isEmpty() ) {
					WHERE("parent_id = #{parentId}");
				}
				if ( role.getDescription() != null && !role.getDescription().isEmpty() ) {
					WHERE("description = #{description}");
				}
				if ( role.getCreatorId() != null && !role.getCreatorId().isEmpty() ) {
					WHERE("creator_id = #{creatorId}");
				}
				if ( role.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
				if ( role.getUpdatorId() != null && !role.getUpdatorId().isEmpty() ) {
					WHERE("updator_id = #{updatorId}");
				}
				if ( role.getUpdateTime() != null ) {
					WHERE("update_time = #{updateTime}");
				}
				WHERE("deleted = 0");
			}}.toString();
		}
	}	
}
