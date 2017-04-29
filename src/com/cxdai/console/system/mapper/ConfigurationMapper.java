package com.cxdai.console.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.system.entity.Configuration;

public interface ConfigurationMapper {

	List<Configuration> selectAll();

	/**
	 * <p>
	 * Description:根据 type 获取 数据字典 集合<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月22日
	 * @return List<Configuration>
	 */
	List<Map<String, Object>> selectConfigurationByConn(Configuration configuration);

	Configuration selectByType(int type);

//	@Update("update rocky_configuration set name=#{name},value=#{value} where type=#{type} and status=0")
//	@ResultType(Integer.class)
//	int updateByType(@Param("name") int name, @Param("value") String tenderBidDate, @Param("type") int type) throws Exception;

	@Update("update rocky_configuration set name=#{name},value=#{value},`order`=#{order} where type=#{type} and status=0")
	@ResultType(Integer.class)
	int updateByType(@Param("name") String name, @Param("value") String value, @Param("type") int type, @Param("order") int order) throws Exception;
	
	
	Integer queeyMaxId();
	Integer insert(Configuration record);
}