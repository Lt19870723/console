package com.cxdai.console.common.page.dialect;

/**
 * MySQL的分页方言
 * <p>
 * Description:MySQL的分页方言<br />
 * </p>
 * 
 * @title MySQLDialect.java
 * @package com.dialect
 * @author qiongbiao.zhang
 * @version 0.1 2014年4月18日
 */
public class MySQLDialect extends Dialect {
	
	/**
	 * 生成分页sql如下:
	 * 
	 * <pre>
	 * select [column(s)] from [Table] order by [column] asc|desc limit :offset, :limit
	 * </pre>
	 * 取值: 从offset + 1条开始, 取limit条.
	 * 
	 */
	public String getLimitString(String sql) {
		StringBuilder sb = new StringBuilder(sql.length() + 11);
		sb.append(sql).append(" limit ?, ?");
		return sb.toString();
	}

}
