package com.cxdai.console.common.page.dialect;

/**
 * Oracle的分页方言
 * <p>
 * Description:Oracle的分页方言<br />
 * </p>
 * 
 * @title OracleDialect.java
 * @package com.dialect
 * @author qiongbiao.zhang
 * @version 0.1 2014年4月18日
 */
public class OracleDialect extends Dialect {

	/**
	 * 生成分页sql如下:
	 * 
	 * <pre>
	 * select * from (
	 *   select t_.*, rownum rn_ from (
	 *     -- 业务sql
	 *   ) t_ where rownum <= :offset + :limit
	 * ) where rn_ >= :offset + 1
	 * </pre>
	 * 
	 * 取值: 从offset + 1条开始, 取limit条.
	 */
	public String getLimitString(String sql) {
		StringBuilder sb = new StringBuilder(sql.length() + 87);
		sb.append("select * from ( select t_.*, rownum rn_ from ( ");
		sb.append(sql);
		sb.append(" ) t_ where rownum <= ? ) where rn_ >= ?");
		return sb.toString();
	}
}