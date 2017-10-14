
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

import org.beanpod.auth.entity.User;

/**
 * 
 * User鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IUserDao {

	@Results(id="user",value={
		@Result(column="id", property="id"),
		@Result(column="name", property="name"),
		@Result(column="password", property="password"),
		@Result(column="creator_id", property="creatorId"),
		@Result(column="parent_id", property="parentId"),
		@Result(column="create_time", property="createTime"),
		@Result(column="update_time", property="updateTime"),
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞User
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( User user );

	/**
	 * 
	 * 鎵归噺娣诲姞User
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<User> userList );
    
	/**
	 * 
	 * 鍒犻櫎User
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎User
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎User
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼User
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( User user );

	/**
	 * 
	 * 鎵归噺淇敼User
	 * 
	 **/
	//int batchUpdate( @Param("user")User user, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨUser
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	User selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨUser鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<User> selectList ( User user );
    
	/**
	 * 
	 * 鏌ヨUser鍒楄〃
	 * 
	 **/
	//List<User> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨUser鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( User user );
    
	/**
	 * 
	 * 鏌ヨUser鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(User user) {
			return new SQL(){{
				INSERT_INTO("t_sys_user");
				if ( user.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( user.getName() == null) {
					VALUES("name", "NULL");
				} else {
					VALUES("name", "#{name}");
				}
				if ( user.getPassword() == null) {
					VALUES("password", "NULL");
				} else {
					VALUES("password", "#{password}");
				}
				if ( user.getCreatorId() == null) {
					VALUES("creator_id", "NULL");
				} else {
					VALUES("creator_id", "#{creatorId}");
				}
				if ( user.getParentId() == null) {
					VALUES("parent_id", "NULL");
				} else {
					VALUES("parent_id", "#{parentId}");
				}
				VALUES("create_time", "SYSDATE()");
				VALUES("update_time", "SYSDATE()");
				VALUES("deleted", "0");
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<User>> map ) {
			boolean genId = false;
	        List<User> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_user (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("name, ");
			sb.append("password, ");
			sb.append("creator_id, ");
			sb.append("parent_id, ");
			sb.append("create_time, ");
			sb.append("update_time, ");
			sb.append("deleted");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].name}, ");
			patternSb.append("#'{'list[{0}].password}, ");
			patternSb.append("#'{'list[{0}].creatorId}, ");
			patternSb.append("#'{'list[{0}].parentId}, ");
			patternSb.append("SYSDATE(), ");
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
				UPDATE("t_sys_user");
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
				UPDATE("t_sys_user");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( User user ) {
			return new SQL(){{
				UPDATE("t_sys_user");
				if ( user.getId() != null) {
					SET("id = #{id}");
				}
				if ( user.getName() != null) {
					SET("name = #{name}");
				}
				if ( user.getPassword() != null) {
					SET("password = #{password}");
				}
				if ( user.getCreatorId() != null) {
					SET("creator_id = #{creatorId}");
				}
				if ( user.getParentId() != null) {
					SET("parent_id = #{parentId}");
				}
				SET("update_time = SYSDATE()");
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_user");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( User user ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_user");
				if ( user.getId() != null && !user.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( user.getName() != null && !user.getName().isEmpty() ) {
					WHERE("name = #{name}");
				}
				if ( user.getPassword() != null && !user.getPassword().isEmpty() ) {
					WHERE("password = #{password}");
				}
				if ( user.getCreatorId() != null && !user.getCreatorId().isEmpty() ) {
					WHERE("creator_id = #{creatorId}");
				}
				if ( user.getParentId() != null && !user.getParentId().isEmpty() ) {
					WHERE("parent_id = #{parentId}");
				}
				if ( user.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
				if ( user.getUpdateTime() != null ) {
					WHERE("update_time = #{updateTime}");
				}
				WHERE("deleted = 0");
			}}.toString();
		}
	}	
}
