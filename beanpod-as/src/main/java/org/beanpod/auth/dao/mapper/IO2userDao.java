
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

import org.beanpod.auth.entity.O2user;

/**
 * 
 * O2user鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IO2userDao {

	@Results(id="o2user",value={
		@Result(column="id", property="id"),
		@Result(column="user_id", property="userId"),
		@Result(column="type", property="type"),
		@Result(column="euid", property="euid"),
		@Result(column="access_token", property="accessToken"),
		@Result(column="refresh_token", property="refreshToken"),
		@Result(column="token_expired_time", property="tokenExpiredTime"),
		@Result(column="create_time", property="createTime")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞O2user
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( O2user o2user );

	/**
	 * 
	 * 鎵归噺娣诲姞O2user
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<O2user> o2userList );
    
	/**
	 * 
	 * 鍒犻櫎O2user
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎O2user
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎O2user
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼O2user
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( O2user o2user );

	/**
	 * 
	 * 鎵归噺淇敼O2user
	 * 
	 **/
	//int batchUpdate( @Param("o2user")O2user o2user, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨO2user
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	O2user selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨO2user鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<O2user> selectList ( O2user o2user );
    
	/**
	 * 
	 * 鏌ヨO2user鍒楄〃
	 * 
	 **/
	//List<O2user> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨO2user鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( O2user o2user );
    
	/**
	 * 
	 * 鏌ヨO2user鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(O2user o2user) {
			return new SQL(){{
				INSERT_INTO("t_sys_o2user");
				if ( o2user.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( o2user.getUserId() == null) {
					VALUES("user_id", "NULL");
				} else {
					VALUES("user_id", "#{userId}");
				}
				if ( o2user.getType() == null) {
					VALUES("type", "NULL");
				} else {
					VALUES("type", "#{type}");
				}
				if ( o2user.getEuid() == null) {
					VALUES("euid", "NULL");
				} else {
					VALUES("euid", "#{euid}");
				}
				if ( o2user.getAccessToken() == null) {
					VALUES("access_token", "NULL");
				} else {
					VALUES("access_token", "#{accessToken}");
				}
				if ( o2user.getRefreshToken() == null) {
					VALUES("refresh_token", "NULL");
				} else {
					VALUES("refresh_token", "#{refreshToken}");
				}
				if ( o2user.getTokenExpiredTime() == null) {
					VALUES("token_expired_time", "NULL");
				} else {
					VALUES("token_expired_time", "#{tokenExpiredTime}");
				}
				VALUES("create_time", "SYSDATE()");
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<O2user>> map ) {
			boolean genId = false;
	        List<O2user> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_o2user (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("user_id, ");
			sb.append("type, ");
			sb.append("euid, ");
			sb.append("access_token, ");
			sb.append("refresh_token, ");
			sb.append("token_expired_time, ");
			sb.append("create_time");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].userId}, ");
			patternSb.append("#'{'list[{0}].type}, ");
			patternSb.append("#'{'list[{0}].euid}, ");
			patternSb.append("#'{'list[{0}].accessToken}, ");
			patternSb.append("#'{'list[{0}].refreshToken}, ");
			patternSb.append("#'{'list[{0}].tokenExpiredTime}, ");
			patternSb.append("SYSDATE()");
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
				UPDATE("t_sys_o2user");
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
				UPDATE("t_sys_o2user");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( O2user o2user ) {
			return new SQL(){{
				UPDATE("t_sys_o2user");
				if ( o2user.getId() != null) {
					SET("id = #{id}");
				}
				if ( o2user.getUserId() != null) {
					SET("user_id = #{userId}");
				}
				if ( o2user.getType() != null) {
					SET("type = #{type}");
				}
				if ( o2user.getEuid() != null) {
					SET("euid = #{euid}");
				}
				if ( o2user.getAccessToken() != null) {
					SET("access_token = #{accessToken}");
				}
				if ( o2user.getRefreshToken() != null) {
					SET("refresh_token = #{refreshToken}");
				}
				if ( o2user.getTokenExpiredTime() != null && o2user.getTokenExpiredTime() != -1) {
					SET("token_expired_time = #{tokenExpiredTime}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_o2user");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( O2user o2user ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_o2user");
				if ( o2user.getId() != null && !o2user.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( o2user.getUserId() != null && !o2user.getUserId().isEmpty() ) {
					WHERE("user_id = #{userId}");
				}
				if ( o2user.getType() != null && !o2user.getType().isEmpty() ) {
					WHERE("type = #{type}");
				}
				if ( o2user.getEuid() != null && !o2user.getEuid().isEmpty() ) {
					WHERE("euid = #{euid}");
				}
				if ( o2user.getAccessToken() != null && !o2user.getAccessToken().isEmpty() ) {
					WHERE("access_token = #{accessToken}");
				}
				if ( o2user.getRefreshToken() != null && !o2user.getRefreshToken().isEmpty() ) {
					WHERE("refresh_token = #{refreshToken}");
				}
				if ( o2user.getTokenExpiredTime() != null && o2user.getTokenExpiredTime() != -1) {
					WHERE("token_expired_time = #{tokenExpiredTime}");
				}
				if ( o2user.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
			}}.toString();
		}
	}	
}
