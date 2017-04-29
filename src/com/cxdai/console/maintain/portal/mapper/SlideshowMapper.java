package com.cxdai.console.maintain.portal.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.Slideshow;
import com.cxdai.console.maintain.portal.entity.SlideshowCnd;
import com.cxdai.console.maintain.portal.entity.SlideshowOperateLog;

public interface SlideshowMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);

	List<?> queryPageAllSlideshowListByCnd(@Param("slideshowCnd") SlideshowCnd slideshowCnd, Page page);

	int queryPageCountSlideshowListByCnd(@Param("slideshowCnd") SlideshowCnd slideshowCnd);

	Slideshow queryByParam(SlideshowCnd slideshowCndUpdate);

	int insertLog(SlideshowOperateLog slideshowOperateLog);

	int getMaxOrder(int type1);

}