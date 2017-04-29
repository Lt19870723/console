package com.cxdai.console.common.page.dialect;

/**
 * SQL方言
 * <p>
 * Description:SQL方言(仅用于分页)<br />
 * </p>
 * 
 * @title Dialect.java
 * @package com.dialect
 * @author qiongbiao.zhang
 * @version 0.1 2014年4月18日
 */
public abstract class Dialect {

	/**
	 * 获取分页sql
	 */
	public abstract String getLimitString(String sql);
}
