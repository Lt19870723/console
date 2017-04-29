package com.cxdai.console.common;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;

public abstract class BaseController {

	protected ModelAndView forword(String viewName) {
		return new ModelAndView(viewName);
	}

	protected ModelAndView forword(String viewName, String modelName, Object modelObject) {
		return new ModelAndView(viewName, modelName, modelObject);
	}

	protected ModelAndView forword(String viewName, Map<String, ?> modelMap) {
		return new ModelAndView(viewName, modelMap);
	}

	protected ModelAndView redirect(String viewName) {
		return new ModelAndView("redirect:" + viewName);
	}

	protected ModelAndView redirect(String viewName, String modelName, Object modelObject) {
		return new ModelAndView("redirect:" + viewName, modelName, modelObject);
	}

	protected ModelAndView redirect(String viewName, Map<String, ?> modelMap) {
		return new ModelAndView("redirect:" + viewName, modelMap);
	}

	protected HttpServletRequest currentRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) requestAttributes).getRequest();
	}
    
	protected HttpSession currentSession() {
		
		return currentRequest().getSession();
	}

	protected ShiroUser currentUser() {
		return ShiroUtils.currentUser();
	}

	protected boolean isLogin() {
		return ShiroUtils.isLogin();
	}

	protected boolean hasRole(String role) {
		return ShiroUtils.hasRole(role);
	}

	protected boolean hasAnyRoles(String... roles) {
		return ShiroUtils.hasAllRoles(roles);
	}

	protected boolean isPermitted(String permission) {
		return ShiroUtils.isPermitted(permission);
	}

	protected boolean isPermittedAny(String... permissions) {
		return ShiroUtils.isPermittedAny(permissions);
	}

	protected boolean isPermittedAll(String... permissions) {
		return ShiroUtils.isPermittedAny(permissions);
	}

	protected void stackTraceError(Logger logger, Throwable e) {
		logger.error(null, e);
	}

	protected UserVo getCurrentUserVo() {
		UserVo userVo = new UserVo();
		userVo.setId(currentUser().getUserId());
		userVo.setUserName(currentUser().getUserName());
		return userVo;
		
	}
	 /**

     * 添加时间的属性编辑器

     */

    @InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
          
          bin.registerCustomEditor(Date.class, new MyCustomDateEditor(
                  new SimpleDateFormat(DateUtils.YMD_HMS), true));
          

    }
    
    public class MyCustomDateEditor extends PropertyEditorSupport {

    	private final DateFormat dateFormat;

    	private final boolean allowEmpty;

    	private final int exactDateLength;


    	public MyCustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
    		this.dateFormat = dateFormat;
    		this.allowEmpty = allowEmpty;
    		this.exactDateLength = -1;
    	}

    	public MyCustomDateEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
    		this.dateFormat = dateFormat;
    		this.allowEmpty = allowEmpty;
    		this.exactDateLength = exactDateLength;
    	}


    	/**
    	 * Parse the Date from the given text, using the specified DateFormat.
    	 */
    	@Override
    	public void setAsText(String text) throws IllegalArgumentException {
    		if (this.allowEmpty && !StringUtils.hasText(text)) {
    			// Treat empty String as null value.
    			setValue(null);
    		}
    		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
    			throw new IllegalArgumentException(
    					"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
    		}
    		else {
    			try {
    				if (text.length() == 8) {
    					setValue(new SimpleDateFormat(DateUtils.YMD).parse(text));
    				} else if (text.length() == 10) {
    					setValue(new SimpleDateFormat(DateUtils.YMD_DASH).parse(text));
    				} else {
    					setValue(this.dateFormat.parse(text));
    				}
    				
    			}
    			catch (ParseException ex) {
    				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
    			}
    		}
    	}

    	/**
    	 * Format the Date as String, using the specified DateFormat.
    	 */
    	@Override
    	public String getAsText() {
    		Date value = (Date) getValue();
    		return (value != null ? this.dateFormat.format(value) : "");
    	}

    }
    
    protected HttpClient getClient() {
		HttpClient client = (HttpClient) currentSession().getAttribute("client");
		if (client == null) {
			client = new HttpClient();
			client.getHttpConnectionManager().closeIdleConnections(30 * 60 * 1000);
			currentSession().setAttribute("client", client);
		}
		return client;
	}
 
}