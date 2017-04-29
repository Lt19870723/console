package com.cxdai.console.util.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PhoneService {
	// 手机验证cache配置名字
	private static final String phoneCache = "phoneCache";

	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * <p>
	 * Description:返回验证码<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月29日
	 * @param phoneNumber 手机号码
	 * @param modelName 功能名称
	 * @return String
	 * @throws Exception
	 */
	public String querySmsValidate(String phoneNumber, String modelName) throws Exception {
		String key = modelName + "_" + phoneNumber;
		String validateNum = String.valueOf(Math.round(Math.random() * 899999 + 100000));
		
		Cache cache = cacheManager.getCache(phoneCache);
		ValueWrapper valueWrapper = cache.get(key);
		if (valueWrapper != null) {
			cache.evict(key);
		}
		System.out.println("手机验证码"+validateNum);
		cache.put(key, validateNum);
		return validateNum;
	}

	/**
	 * <p>
	 * Description：比较手机号码对应的验证码是否一致<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月29日
	 * @param mobile
	 * @param phoneValidate 手机号码
	 * @param modelName 功能名称
	 * @return String
	 * @throws Exception
	 */
	public String compareSmsValidate(String mobile, String phoneValidate, String modelName) throws Exception {
		String key = modelName + "_" + mobile;
		
		Cache cache = cacheManager.getCache(phoneCache);
		ValueWrapper valueWrapper = cache.get(key);
		
		if (valueWrapper != null) {
			if (phoneValidate.equals((String) valueWrapper.get())) {
				cache.evict(key);
				return "success";
			} else {
				return "验证码不正确";
			}
		} else {
			return "验证码已过期";
		}
	}

}
