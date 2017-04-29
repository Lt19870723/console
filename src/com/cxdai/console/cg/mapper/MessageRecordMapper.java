package com.cxdai.console.cg.mapper;

import com.cxdai.console.cg.entity.MessageRecord;
import org.apache.ibatis.annotations.Param;

public interface MessageRecordMapper {

    int insert(MessageRecord messageRecord);

    String queryLastMessage(@Param("filename") String filename);

}