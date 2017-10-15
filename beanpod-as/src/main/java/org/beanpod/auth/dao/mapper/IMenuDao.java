
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

import org.beanpod.auth.entity.Menu;

/**
 * 
 * Menu鏁版嵁鎿嶄綔鎺ュ彛绫�
 * 
 **/
@Mapper
public interface IMenuDao {

	@Results(id="menu",value={
		@Result(column="id", property="id"),
		@Result(column="name", property="name"),
		@Result(column="display_name", property="displayName"),
		@Result(column="type", property="type"),
		@Result(column="is_sys", property="isSys"),
		@Result(column="usable", property="usable"),
		@Result(column="show", property="show"),
		@Result(column="parent_id", property="parentId"),
		@Result(column="level", property="level"),
		@Result(column="order_number", property="orderNumber"),
		@Result(column="href", property="href"),
		@Result(column="target", property="target"),
		@Result(column="icon", property="icon"),
		@Result(column="description", property="description"),
		@Result(column="creator_id", property="creatorId"),
		@Result(column="create_time", property="createTime"),
		@Result(column="updator_id", property="updatorId"),
		@Result(column="update_time", property="updateTime"),
	})
	
	// @Options(useGeneratedKeys=true,keyProperty="id")
	
	/**
	 * 
	 * 娣诲姞Menu
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insert")
	@SelectKey(statement = "select replace(uuid(),'-','')", keyColumn="id", keyProperty = "id", before = true, resultType = String.class)
	int insert( Menu menu );

	/**
	 * 
	 * 鎵归噺娣诲姞Menu
	 * 
	 **/
	@InsertProvider(type = SqlProvider.class, method = "insertAll")
	int insertAll( List<Menu> menuList );
    
	/**
	 * 
	 * 鍒犻櫎Menu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteById")
	int deleteById ( String id );

	/**
	 * 
	 * 鎵归噺鍒犻櫎Menu
	 * 
	 **/
	//int batchDelete ( Map<String, ?> params );
    
    
	/**
	 * 
	 * 鎵归噺鍒犻櫎Menu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "deleteAllByIds")
	int deleteAllByIds ( List<String> list );
    
	/**
	 * 
	 * 淇敼Menu
	 * 
	 **/
	@UpdateProvider(type = SqlProvider.class, method = "updateById")
	int updateById( Menu menu );

	/**
	 * 
	 * 鎵归噺淇敼Menu
	 * 
	 **/
	//int batchUpdate( @Param("menu")Menu menu, @Param("params") Map<String, ?> params );

	/**
	 * 
	 * 鏌ヨMenu
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectById")
	Menu selectById ( String id );
    
	/**
	 * 
	 * 鏌ヨMenu鍒楄〃
	 * 
	 **/
	@SelectProvider(type = SqlProvider.class, method = "selectList")
    List<Menu> selectList ( Menu menu );
    
	/**
	 * 
	 * 鏌ヨMenu鍒楄〃
	 * 
	 **/
	//List<Menu> selectListByMap ( Map<String, ?> params );
    
	/**
	 * 
	 * 鏌ヨMenu鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSize ( Menu menu );
    
	/**
	 * 
	 * 鏌ヨMenu鍒楄〃澶у皬
	 * 
	 **/
	//Integer selectListSizeByMap ( Map<String, ?> params );
  
	class SqlProvider {
		public String insert(Menu menu) {
			return new SQL(){{
				INSERT_INTO("t_sys_menu");
				if ( menu.getId() == null) {
					VALUES("id", "replace(uuid(),'-','')");
				} else {
					VALUES("id", "#{id}");
				}
				if ( menu.getName() == null) {
					VALUES("name", "NULL");
				} else {
					VALUES("name", "#{name}");
				}
				if ( menu.getDisplayName() == null) {
					VALUES("display_name", "NULL");
				} else {
					VALUES("display_name", "#{displayName}");
				}
				if ( menu.getType() == null) {
					VALUES("type", "NULL");
				} else {
					VALUES("type", "#{type}");
				}
				if ( menu.getIsSys() == null) {
					VALUES("is_sys", "NULL");
				} else {
					VALUES("is_sys", "#{isSys}");
				}
				if ( menu.getUsable() == null) {
					VALUES("usable", "NULL");
				} else {
					VALUES("usable", "#{usable}");
				}
				if ( menu.getShow() == null) {
					VALUES("show", "NULL");
				} else {
					VALUES("show", "#{show}");
				}
				if ( menu.getParentId() == null) {
					VALUES("parent_id", "NULL");
				} else {
					VALUES("parent_id", "#{parentId}");
				}
				if ( menu.getLevel() == null) {
					VALUES("level", "NULL");
				} else {
					VALUES("level", "#{level}");
				}
				if ( menu.getOrderNumber() == null) {
					VALUES("order_number", "NULL");
				} else {
					VALUES("order_number", "#{orderNumber}");
				}
				if ( menu.getHref() == null) {
					VALUES("href", "NULL");
				} else {
					VALUES("href", "#{href}");
				}
				if ( menu.getTarget() == null) {
					VALUES("target", "NULL");
				} else {
					VALUES("target", "#{target}");
				}
				if ( menu.getIcon() == null) {
					VALUES("icon", "NULL");
				} else {
					VALUES("icon", "#{icon}");
				}
				if ( menu.getDescription() == null) {
					VALUES("description", "NULL");
				} else {
					VALUES("description", "#{description}");
				}
				if ( menu.getCreatorId() == null) {
					VALUES("creator_id", "NULL");
				} else {
					VALUES("creator_id", "#{creatorId}");
				}
				VALUES("create_time", "SYSDATE()");
				if ( menu.getUpdatorId() == null) {
					VALUES("updator_id", "NULL");
				} else {
					VALUES("updator_id", "#{updatorId}");
				}
				VALUES("update_time", "SYSDATE()");
				VALUES("deleted", "0");
			}}.toString();
		}
		
	    public String insertAll( Map<String, List<Menu>> map ) {
			boolean genId = false;
	        List<Menu> list = map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("INSERT INTO t_sys_menu (");
	        // sb.append("(id, name, password, creator_id, parent_id, create_time, update_time, deleted)");  
			sb.append("id, ");
			sb.append("name, ");
			sb.append("display_name, ");
			sb.append("type, ");
			sb.append("is_sys, ");
			sb.append("usable, ");
			sb.append("show, ");
			sb.append("parent_id, ");
			sb.append("level, ");
			sb.append("order_number, ");
			sb.append("href, ");
			sb.append("target, ");
			sb.append("icon, ");
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
			patternSb.append("#'{'list[{0}].type}, ");
			patternSb.append("#'{'list[{0}].isSys}, ");
			patternSb.append("#'{'list[{0}].usable}, ");
			patternSb.append("#'{'list[{0}].show}, ");
			patternSb.append("#'{'list[{0}].parentId}, ");
			patternSb.append("#'{'list[{0}].level}, ");
			patternSb.append("#'{'list[{0}].orderNumber}, ");
			patternSb.append("#'{'list[{0}].href}, ");
			patternSb.append("#'{'list[{0}].target}, ");
			patternSb.append("#'{'list[{0}].icon}, ");
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
				UPDATE("t_sys_menu");
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
				UPDATE("t_sys_menu");
				SET("deleted = 1");
				WHERE(sb.toString());
			}}.toString();
		}
		
		public String updateById( Menu menu ) {
			return new SQL(){{
				UPDATE("t_sys_menu");
				if ( menu.getId() != null) {
					SET("id = #{id}");
				}
				if ( menu.getName() != null) {
					SET("name = #{name}");
				}
				if ( menu.getDisplayName() != null) {
					SET("display_name = #{displayName}");
				}
				if ( menu.getType() != null && menu.getType() != -1) {
					SET("type = #{type}");
				}
				if ( menu.getIsSys() != null && menu.getIsSys() != -1) {
					SET("is_sys = #{isSys}");
				}
				if ( menu.getUsable() != null && menu.getUsable() != -1) {
					SET("usable = #{usable}");
				}
				if ( menu.getShow() != null && menu.getShow() != -1) {
					SET("show = #{show}");
				}
				if ( menu.getParentId() != null) {
					SET("parent_id = #{parentId}");
				}
				if ( menu.getLevel() != null && menu.getLevel() != -1) {
					SET("level = #{level}");
				}
				if ( menu.getOrderNumber() != null && menu.getOrderNumber() != -1) {
					SET("order_number = #{orderNumber}");
				}
				if ( menu.getHref() != null) {
					SET("href = #{href}");
				}
				if ( menu.getTarget() != null) {
					SET("target = #{target}");
				}
				if ( menu.getIcon() != null) {
					SET("icon = #{icon}");
				}
				if ( menu.getDescription() != null) {
					SET("description = #{description}");
				}
				if ( menu.getCreatorId() != null) {
					SET("creator_id = #{creatorId}");
				}
				if ( menu.getUpdatorId() != null) {
					SET("updator_id = #{updatorId}");
				}
				SET("update_time = SYSDATE()");
				WHERE("id = #{id}");
			}}.toString();
		}
		
		public String selectById(String id) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_menu");
				WHERE("id = #{id}");
				WHERE("deleted = 0");      
			}}.toString();
		}
		
		public String selectList ( Menu menu ) {
			return new SQL(){{      
				SELECT("*");
				FROM("t_sys_menu");
				if ( menu.getId() != null && !menu.getId().isEmpty() ) {
					WHERE("id = #{id}");
				}
				if ( menu.getName() != null && !menu.getName().isEmpty() ) {
					WHERE("name = #{name}");
				}
				if ( menu.getDisplayName() != null && !menu.getDisplayName().isEmpty() ) {
					WHERE("display_name = #{displayName}");
				}
				if ( menu.getType() != null && menu.getType() != -1) {
					WHERE("type = #{type}");
				}
				if ( menu.getIsSys() != null && menu.getIsSys() != -1) {
					WHERE("is_sys = #{isSys}");
				}
				if ( menu.getUsable() != null && menu.getUsable() != -1) {
					WHERE("usable = #{usable}");
				}
				if ( menu.getShow() != null && menu.getShow() != -1) {
					WHERE("show = #{show}");
				}
				if ( menu.getParentId() != null && !menu.getParentId().isEmpty() ) {
					WHERE("parent_id = #{parentId}");
				}
				if ( menu.getLevel() != null && menu.getLevel() != -1) {
					WHERE("level = #{level}");
				}
				if ( menu.getOrderNumber() != null && menu.getOrderNumber() != -1) {
					WHERE("order_number = #{orderNumber}");
				}
				if ( menu.getHref() != null && !menu.getHref().isEmpty() ) {
					WHERE("href = #{href}");
				}
				if ( menu.getTarget() != null && !menu.getTarget().isEmpty() ) {
					WHERE("target = #{target}");
				}
				if ( menu.getIcon() != null && !menu.getIcon().isEmpty() ) {
					WHERE("icon = #{icon}");
				}
				if ( menu.getDescription() != null && !menu.getDescription().isEmpty() ) {
					WHERE("description = #{description}");
				}
				if ( menu.getCreatorId() != null && !menu.getCreatorId().isEmpty() ) {
					WHERE("creator_id = #{creatorId}");
				}
				if ( menu.getCreateTime() != null ) {
					WHERE("create_time = #{createTime}");
				}
				if ( menu.getUpdatorId() != null && !menu.getUpdatorId().isEmpty() ) {
					WHERE("updator_id = #{updatorId}");
				}
				if ( menu.getUpdateTime() != null ) {
					WHERE("update_time = #{updateTime}");
				}
				WHERE("deleted = 0");
			}}.toString();
		}
	}	
}
