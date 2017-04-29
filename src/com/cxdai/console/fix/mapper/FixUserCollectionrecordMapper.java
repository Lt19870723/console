package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixUserCollectionrecord;
import com.cxdai.console.fix.vo.FixUserCollectionrecordCnd;

public interface FixUserCollectionrecordMapper {

	int insert(FixUserCollectionrecord record);

	FixUserCollectionrecord selectById(Integer id);

	int updateById(FixUserCollectionrecord record);
	
	BigDecimal queryCapitalSum(FixUserCollectionrecordCnd fixUserCollectionrecordCnd);
}