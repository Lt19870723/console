package com.cxdai.console.curaccount.vo;

import java.io.Serializable;

import com.cxdai.console.curaccount.entity.CurInterestDetail;
import com.cxdai.console.util.DateUtils;

public class CurInterestDetailVo extends CurInterestDetail implements Serializable {
	
	private static final long serialVersionUID = -6903226903535940857L;
	
	
   //收益日期
    private String interestDateStr;


	/**
	 * 日期格式化 date -- dateStr(2015-5-5)
	 * 
	 * @return interestDateStr : return the property interestDateStr.
	 */
	public String getInterestDateStr() {
		if(super.getInterestDate()!=null)
		{
			return DateUtils.format(super.getInterestDate(), DateUtils.YMD_DASH);
		}
		return interestDateStr;
	}
	

	/**
	 * @param interestDateStr : set the property interestDateStr.
	 */
	public void setInterestDateStr(String interestDateStr) {
		this.interestDateStr = interestDateStr;
	}
	

}