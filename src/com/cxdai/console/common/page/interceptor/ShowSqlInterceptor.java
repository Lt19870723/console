package com.cxdai.console.common.page.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * <p>
 * Description:Mybatis show sql 拦截器<br />
 * </p>
 * 
 * @title ShowSqlInterceptor.java
 * @package com.interceptor
 * @author qiongbiao.zhang
 * @version 0.1 2014年5月28日
 */
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
	@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
public class ShowSqlInterceptor implements Interceptor {
	
	private static final Log logger = LogFactory.getLog(ShowSqlInterceptor.class);
	
	// 慢查询sql时间判断(默认3秒)
	private long slowSqlMillis = 3000L;
	// 是否只输出慢查询sql
	private boolean logSlowSql = false;

	public Object intercept(Invocation invocation) throws Throwable {
		long t = System.currentTimeMillis();
		Object returnValue = invocation.proceed();
		long cost = (System.currentTimeMillis() - t);
		
		boolean isLog = true;
		if (logSlowSql) {
			isLog = (cost >= slowSqlMillis);
		}
		
		if (isLog) {
			final Object[] args = invocation.getArgs();
			final MappedStatement mappedStatement = (MappedStatement) args[0];
			final Object parameter = args[1];
			final BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			final Configuration configuration = mappedStatement.getConfiguration();
			
			String sqlId = mappedStatement.getId();
			String sql = showSql(boundSql);
			Object[] params = showParameters(configuration, boundSql);
			
			logger.info(sqlId + " : " + sql + (params != null ? "\tParams:" + Arrays.deepToString(params) : "") + "\t[costTimes:" + cost + "ms]\n");
		}
		return returnValue;
	}

	private String showSql(BoundSql boundSql) {
		return boundSql.getSql().replaceAll("[\\s]+", " ");
	}
	
	private Object[] showParameters(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings.isEmpty() || parameterObject == null) {
			return null;
		}
		
		List<Object> parameters = new ArrayList<Object>();
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
			parameters.add(getParameterValue(parameterObject));
		} else {
			MetaObject metaObject = configuration.newMetaObject(parameterObject);
			for (ParameterMapping parameterMapping : parameterMappings) {
				String propertyName = parameterMapping.getProperty();
				if (metaObject.hasGetter(propertyName)) {
					parameters.add(getParameterValue(metaObject.getValue(propertyName)));
				} else if (boundSql.hasAdditionalParameter(propertyName)) {
					parameters.add(getParameterValue(boundSql.getAdditionalParameter(propertyName)));
				}
			}
		}
		return parameters.toArray(new Object[0]);
	}
	

//	private static final DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
	private static Object getParameterValue(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof String) {
			return "'" + obj + "'";
		} else if (obj instanceof Date) {
			return "'" + DateFormatUtils.format((Date) obj, "yyyy-MM-dd HH:mm:ss") + "'";
//			return "'" + formatter.format(obj) + "'";
		} else {
			return obj;
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setSlowSqlMillis(long slowSqlMillis) {
		this.slowSqlMillis = slowSqlMillis;
	}

	public void setLogSlowSql(boolean logSlowSql) {
		this.logSlowSql = logSlowSql;
	}

	public void setProperties(Properties properties) {
	}
}