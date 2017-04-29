package com.cxdai.console.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;

@RequestMapping("/cache")
@Controller
public class CacheManageController extends BaseController {

	@Autowired
	private CacheManager cacheManager;

	@RequestMapping(value = "/evict/{name}")
	@ResponseBody
	public MessageBox evict(@PathVariable("name") String name, @RequestParam(value = "key", required = false) String key) {
		try {
			Cache cache = cacheManager.getCache(name);
			if (cache == null) {
				return MessageBox.build("0", "cache name '" + name + "' is not exists");
			}

			if (key == null) {
				cache.clear();
			} else {
				cache.evict(key);
			}
			return MessageBox.build("1", "evict successed.");
		} catch (Exception e) {
			return MessageBox.build("0", e.getMessage());
		}
	}
}
