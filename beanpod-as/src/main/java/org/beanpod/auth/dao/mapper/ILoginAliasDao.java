
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

import org.beanpod.auth.entity.LoginAlias;

/**
 * 
 * LoginAlias鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface ILoginAliasDao {

	@Results(id="loginAlias",value={
		@Result(column="id", property="id"),
		@Result(column="user_id", property="userId"),
		@Result(column="login_type", property="loginType"),
		@Result(column="login_name", property="loginName"),
		@Result(column="verified", property="verified"),
		@Result(column="create_time", property="createTime")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞LoginAlias
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( LoginAlias loginAlias );

	/**
	 * 
	 * 鎵归噺娣诲姞LoginAlias
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<LoginAlias> loginAliasList );
    
	/**
	 * 
	 * 鍒犻櫎LoginAlias
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎LoginAlias
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎LoginAlias
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼LoginAlias
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( LoginAlias loginAlias );

	/**
	 * 
	 * 鎵归噺淇敼LoginAlias
	 * 
	 **/
	//int batchUpdate( @Param("loginAlias")LoginAlias loginAlias, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨLoginAlias
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	LoginAlias selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨLoginAlias鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<LoginAlias> selectList ( LoginAlias loginAlias );
    
	/**
	 * 
	 * 鏌ヨLoginAlias鍒楄〃
	 * 
	 **/
	//List<LoginAlias> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨLoginAlias鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( LoginAlias loginAlias );
    
	/**
	 * 
	 * 鏌ヨLoginAlias鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(LoginAlias loginAlias) {
			return new SQL(){{
				INSERT_INTO("t_sys_login_alias");
				if ( loginAlias.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( loginAlias.getUserId() == null) {
					VALUES("user_id", "NULL");
				} else {
					VALUES("user_id", "#{userId}");
				}
				if ( loginAlias.getLoginType() == null) {
					VALUES("login_type", "NULL");
				} else {
					VALUES("login_type", "#{loginType}");
				}
				if ( loginAlias.getLoginName() == null) {
					VALUES("login_name", "NULL");
				} else {
					VALUES("login_name", "#{loginName}");
				}
				if ( loginAlias.getVerified() == null) {
					VALUES("verified", "NULL");
				} else {
					VALUES("verified", "#{verified}");
				}
				VALUES("create_time", "SYSDATE()");
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<LoginAlias>> map ) {
			boolean genId = false;
	        List<LoginAlias> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_login_alias (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("user_id, ");
			sb.append("login_type, ");
			sb.append("login_name, ");
			sb.append("verified, ");
			sb.append("create_time");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].userId}, ");
			patternSb.append("#'{'list[{0}].loginType}, ");
			patternSb.append("#'{'list[{0}].loginName}, ");
			patternSb.append("#'{'list[{0}].verified}, ");
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
				UPDATE("t_sys_login_alias");
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
				UPDATE("t_sys_login_alias");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( LoginAlias loginAlias ) {
			return new SQL(){{
				UPDATE("t_sys_login_alias");
				if ( loginAlias.getId() != null) {
					SET("id = #{id}");
				}
				if ( loginAlias.getUserId() != null) {
					SET("user_id = #{userId}");
				}
				if ( loginAlias.getLoginType() != null && loginAlias.getLoginType() != -1) {
					SET("login_type = #{loginType}");
				}
				if ( loginAlias.getLoginName() != null) {
					SET("login_name = #{loginName}");
				}
				if ( loginAlias.getVerified() != null && loginAlias.getVerified() != -1) {
					SET("verified = #{verified}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_login_alias");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( LoginAlias loginAlias ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_login_alias");
				if ( loginAlias.getId() != null && !loginAlias.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( loginAlias.getUserId() != null && !loginAlias.getUserId().isEmpty() ) {
					WHERE("user_id = #{userId}");
				}
				if ( loginAlias.getLoginType() != null && loginAlias.getLoginType() != -1) {
					WHERE("login_type = #{loginType}");
				}
				if ( loginAlias.getLoginName() != null && !loginAlias.getLoginName().isEmpty() ) {
					WHERE("login_name = #{loginName}");
				}
				if ( loginAlias.getVerified() != null && loginAlias.getVerified() != -1) {
					WHERE("verified = #{verified}");
				}
				if ( loginAlias.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
			}}.toString();
		}
	}	
}
