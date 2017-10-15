
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

import org.beanpod.auth.entity.TokenPermission;

/**
 * 
 * TokenPermission鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface ITokenPermissionDao {

	@Results(id="tokenPermission",value={
		@Result(column="id", property="id"),
		@Result(column="token_id", property="tokenId"),
		@Result(column="permission_id", property="permissionId")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞TokenPermission
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( TokenPermission tokenPermission );

	/**
	 * 
	 * 鎵归噺娣诲姞TokenPermission
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<TokenPermission> tokenPermissionList );
    
	/**
	 * 
	 * 鍒犻櫎TokenPermission
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎TokenPermission
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎TokenPermission
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼TokenPermission
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( TokenPermission tokenPermission );

	/**
	 * 
	 * 鎵归噺淇敼TokenPermission
	 * 
	 **/
	//int batchUpdate( @Param("tokenPermission")TokenPermission tokenPermission, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨTokenPermission
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	TokenPermission selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨTokenPermission鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<TokenPermission> selectList ( TokenPermission tokenPermission );
    
	/**
	 * 
	 * 鏌ヨTokenPermission鍒楄〃
	 * 
	 **/
	//List<TokenPermission> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨTokenPermission鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( TokenPermission tokenPermission );
    
	/**
	 * 
	 * 鏌ヨTokenPermission鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(TokenPermission tokenPermission) {
			return new SQL(){{
				INSERT_INTO("t_sys_token_permission");
				if ( tokenPermission.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( tokenPermission.getTokenId() == null) {
					VALUES("token_id", "NULL");
				} else {
					VALUES("token_id", "#{tokenId}");
				}
				if ( tokenPermission.getPermissionId() == null) {
					VALUES("permission_id", "NULL");
				} else {
					VALUES("permission_id", "#{permissionId}");
				}
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<TokenPermission>> map ) {
			boolean genId = false;
	        List<TokenPermission> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_token_permission (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("token_id, ");
			sb.append("permission_id");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].tokenId}, ");
			patternSb.append("#'{'list[{0}].permissionId}");
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
				UPDATE("t_sys_token_permission");
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
				UPDATE("t_sys_token_permission");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( TokenPermission tokenPermission ) {
			return new SQL(){{
				UPDATE("t_sys_token_permission");
				if ( tokenPermission.getId() != null) {
					SET("id = #{id}");
				}
				if ( tokenPermission.getTokenId() != null) {
					SET("token_id = #{tokenId}");
				}
				if ( tokenPermission.getPermissionId() != null) {
					SET("permission_id = #{permissionId}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_token_permission");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( TokenPermission tokenPermission ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_token_permission");
				if ( tokenPermission.getId() != null && !tokenPermission.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( tokenPermission.getTokenId() != null && !tokenPermission.getTokenId().isEmpty() ) {
					WHERE("token_id = #{tokenId}");
				}
				if ( tokenPermission.getPermissionId() != null && !tokenPermission.getPermissionId().isEmpty() ) {
					WHERE("permission_id = #{permissionId}");
				}
			}}.toString();
		}
	}	
}
