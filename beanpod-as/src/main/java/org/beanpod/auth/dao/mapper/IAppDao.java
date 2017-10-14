
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

import org.beanpod.auth.entity.App;

/**
 * 
 * App鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IAppDao {

	@Results(id="app",value={
		@Result(column="id", property="id"),
		@Result(column="ower_id", property="owerId"),
		@Result(column="available", property="available"),
		@Result(column="deteted", property="deteted")
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞App
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( App app );

	/**
	 * 
	 * 鎵归噺娣诲姞App
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<App> appList );
    
	/**
	 * 
	 * 鍒犻櫎App
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎App
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎App
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼App
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( App app );

	/**
	 * 
	 * 鎵归噺淇敼App
	 * 
	 **/
	//int batchUpdate( @Param("app")App app, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨApp
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	App selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨApp鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<App> selectList ( App app );
    
	/**
	 * 
	 * 鏌ヨApp鍒楄〃
	 * 
	 **/
	//List<App> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨApp鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( App app );
    
	/**
	 * 
	 * 鏌ヨApp鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(App app) {
			return new SQL(){{
				INSERT_INTO("t_sys_app");
				if ( app.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( app.getOwerId() == null) {
					VALUES("ower_id", "NULL");
				} else {
					VALUES("ower_id", "#{owerId}");
				}
				if ( app.getAvailable() == null) {
					VALUES("available", "NULL");
				} else {
					VALUES("available", "#{available}");
				}
				if ( app.getDeteted() == null) {
					VALUES("deteted", "NULL");
				} else {
					VALUES("deteted", "#{deteted}");
				}
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<App>> map ) {
			boolean genId = false;
	        List<App> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_app (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("ower_id, ");
			sb.append("available, ");
			sb.append("deteted");
	        sb.append(") VALUES ");
			StringBuilder patternSb = new StringBuilder();
			patternSb.append("(");
			genId = true;
			patternSb.append("#'{'list[{0}].id}, ");
			patternSb.append("#'{'list[{0}].owerId}, ");
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
				UPDATE("t_sys_app");
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
				UPDATE("t_sys_app");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( App app ) {
			return new SQL(){{
				UPDATE("t_sys_app");
				if ( app.getId() != null) {
					SET("id = #{id}");
				}
				if ( app.getOwerId() != null) {
					SET("ower_id = #{owerId}");
				}
				if ( app.getAvailable() != null && app.getAvailable() != -1) {
					SET("available = #{available}");
				}
				if ( app.getDeteted() != null && app.getDeteted() != -1) {
					SET("deteted = #{deteted}");
				}
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_app");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( App app ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_app");
				if ( app.getId() != null && !app.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( app.getOwerId() != null && !app.getOwerId().isEmpty() ) {
					WHERE("ower_id = #{owerId}");
				}
				if ( app.getAvailable() != null && app.getAvailable() != -1) {
					WHERE("available = #{available}");
				}
				if ( app.getDeteted() != null && app.getDeteted() != -1) {
					WHERE("deteted = #{deteted}");
				}
			}}.toString();
		}
	}	
}
