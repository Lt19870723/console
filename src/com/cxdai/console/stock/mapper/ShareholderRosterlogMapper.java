package com.cxdai.console.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.stock.entity.ShareholderRosterlog;

public interface ShareholderRosterlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareholderRosterlog record);

    int insertSelective(ShareholderRosterlog record);

    ShareholderRosterlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareholderRosterlog record);

    int updateByPrimaryKey(ShareholderRosterlog record);
    
    public List<ShareholderRosterlog> shareholderLogList(@Param("status") int status, @Param("userId") int userId);
}