
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

import org.beanpod.auth.entity.AccessToken;

/**
 * 
 * AccessToken鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IAccessTokenDao {

	@Results(id="accessToken",value={
		@Result(column="id", property="id"),
		@Result(column="type", property="type"),
		@Result(column="user_id", property="userId"),
		@Result(column="app_id", property="appId"),
		@Result(column="access_token", property="accessToken"),
		@Result(column="refresh_token", property="refreshToken"),
		@Result(column="create_time", property="createTime"),
		@Result(column="refresh_time", property="refreshTime"),
		@Result(column="expired_time", property="expiredTime"),
		@Result(column="refresh_expired_time", property="refreshExpiredTime"),
		@Result(column="available", property="available"),
		@Result(column="deteted", property="deteted")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞AccessToken
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( AccessToken accessToken );

	/**
	 * 
	 * 鎵归噺娣诲姞AccessToken
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<AccessToken> accessTokenList );
    
	/**
	 * 
	 * 鍒犻櫎AccessToken
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎AccessToken
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎AccessToken
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼AccessToken
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( AccessToken accessToken );

	/**
	 * 
	 * 鎵归噺淇敼AccessToken
	 * 
	 **/
	//int batchUpdate( @Param("accessToken")AccessToken accessToken, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨAccessToken
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	AccessToken selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨAccessToken鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<AccessToken> selectList ( AccessToken accessToken );
    
	/**
	 * 
	 * 鏌ヨAccessToken鍒楄〃
	 * 
	 **/
	//List<AccessToken> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨAccessToken鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( AccessToken accessToken );
    
	/**
	 * 
	 * 鏌ヨAccessToken鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(AccessToken accessToken) {
			return new SQL(){{
				INSERT_INTO("t_sys_access_token");
				if ( accessToken.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( accessToken.getType() == null) {
					VALUES("type", "NULL");
				} else {
					VALUES("type", "#{type}");
				}
				if ( accessToken.getUserId() == null) {
					VALUES("user_id", "NULL");
				} else {
					VALUES("user_id", "#{userId}");
				}
				if ( accessToken.getAppId() == null) {
					VALUES("app_id", "NULL");
				} else {
					VALUES("app_id", "#{appId}");
				}
				if ( accessToken.getAccessToken() == null) {
					VALUES("access_token", "NULL");
				} else {
					VALUES("access_token", "#{accessToken}");
				}
				if ( accessToken.getRefreshToken() == null) {
					VALUES("refresh_token", "NULL");
				} else {
					VALUES("refresh_token", "#{refreshToken}");
				}
				VALUES("create_time", "SYSDATE()");
				if ( accessToken.getRefreshTime() == null) {
					VALUES("refresh_time", "NULL");
				} else {
					VALUES("refresh_time", "#{refreshTime}");
				}
				if ( accessToken.getExpiredTime() == null) {
					VALUES("expired_time", "NULL");
				} else {
					VALUES("expired_time", "#{expiredTime}");
				}
				if ( accessToken.getRefreshExpiredTime() == null) {
					VALUES("refresh_expired_time", "NULL");
				} else {
					VALUES("refresh_expired_time", "#{refreshExpiredTime}");
				}
				if ( accessToken.getAvailable() == null) {
					VALUES("available", "NULL");
				} else {
					VALUES("available", "#{available}");
				}
				if ( accessToken.getDeteted() == null) {
					VALUES("deteted", "NULL");
				} else {
					VALUES("deteted", "#{deteted}");
				}
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<AccessToken>> map ) {
			boolean genId = false;
	        List<AccessToken> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_access_token (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("type, ");
			sb.append("user_id, ");
			sb.append("app_id, ");
			sb.append("access_token, ");
			sb.append("refresh_token, ");
			sb.append("create_time, ");
			sb.append("refresh_time, ");
			sb.append("expired_time, ");
			sb.append("refresh_expired_time, ");
			sb.append("available, ");
			sb.append("deteted");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].type}, ");
			patternSb.append("#'{'list[{0}].userId}, ");
			patternSb.append("#'{'list[{0}].appId}, ");
			patternSb.append("#'{'list[{0}].accessToken}, ");
			patternSb.append("#'{'list[{0}].refreshToken}, ");
			patternSb.append("SYSDATE(), ");
			patternSb.append("#'{'list[{0}].refreshTime}, ");
			patternSb.append("#'{'list[{0}].expiredTime}, ");
			patternSb.append("#'{'list[{0}].refreshExpiredTime}, ");
			patternSb.append("#'{'list[{0}].available}, ");
			patternSb.append("#'{'list[{0}].deteted}");
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
				UPDATE("t_sys_access_token");
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
				UPDATE("t_sys_access_token");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( AccessToken accessToken ) {
			return new SQL(){{
				UPDATE("t_sys_access_token");
				if ( accessToken.getId() != null) {
					SET("id = #{id}");
				}
				if ( accessToken.getType() != null && accessToken.getType() != -1) {
					SET("type = #{type}");
				}
				if ( accessToken.getUserId() != null) {
					SET("user_id = #{userId}");
				}
				if ( accessToken.getAppId() != null) {
					SET("app_id = #{appId}");
				}
				if ( accessToken.getAccessToken() != null) {
					SET("access_token = #{accessToken}");
				}
				if ( accessToken.getRefreshToken() != null) {
					SET("refresh_token = #{refreshToken}");
				}
				if ( accessToken.getRefreshTime() != null) {
					SET("refresh_time = #{refreshTime}");
				}
				if ( accessToken.getExpiredTime() != null) {
					SET("expired_time = #{expiredTime}");
				}
				if ( accessToken.getRefreshExpiredTime() != null) {
					SET("refresh_expired_time = #{refreshExpiredTime}");
				}
				if ( accessToken.getAvailable() != null && accessToken.getAvailable() != -1) {
					SET("available = #{available}");
				}
				if ( accessToken.getDeteted() != null && accessToken.getDeteted() != -1) {
					SET("deteted = #{deteted}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_access_token");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( AccessToken accessToken ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_access_token");
				if ( accessToken.getId() != null && !accessToken.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( accessToken.getType() != null && accessToken.getType() != -1) {
					WHERE("type = #{type}");
				}
				if ( accessToken.getUserId() != null && !accessToken.getUserId().isEmpty() ) {
					WHERE("user_id = #{userId}");
				}
				if ( accessToken.getAppId() != null && !accessToken.getAppId().isEmpty() ) {
					WHERE("app_id = #{appId}");
				}
				if ( accessToken.getAccessToken() != null && !accessToken.getAccessToken().isEmpty() ) {
					WHERE("access_token = #{accessToken}");
				}
				if ( accessToken.getRefreshToken() != null && !accessToken.getRefreshToken().isEmpty() ) {
					WHERE("refresh_token = #{refreshToken}");
				}
				if ( accessToken.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
				if ( accessToken.getRefreshTime() != null ) {
					WHERE("refresh_time = #{refreshTime}");
				}
				if ( accessToken.getExpiredTime() != null ) {
					WHERE("expired_time = #{expiredTime}");
				}
				if ( accessToken.getRefreshExpiredTime() != null ) {
					WHERE("refresh_expired_time = #{refreshExpiredTime}");
				}
				if ( accessToken.getAvailable() != null && accessToken.getAvailable() != -1) {
					WHERE("available = #{available}");
				}
				if ( accessToken.getDeteted() != null && accessToken.getDeteted() != -1) {
					WHERE("deteted = #{deteted}");
				}
			}}.toString();
		}
	}	
}
