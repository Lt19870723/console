package com.cxdai.console.system.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.mapper.ConfigurationMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ConfigurationService {

	@Autowired
	private ConfigurationMapper configurationMapper;

	@Transactional(rollbackFor = Throwable.class, readOnly = true)
	@Cacheable(value = "queryAllConfigurations")
	public HashMap<Integer, LinkedHashMap<String, Configuration>> queryAllConfigurations() {
		List<Configuration> configurations = configurationMapper.selectAll();

		HashMap<Integer, LinkedHashMap<String, Configuration>> ret = new HashMap<Integer, LinkedHashMap<String, Configuration>>();
		Integer type = null;
		for (Configuration configuration : configurations) {
			if (!ret.containsKey(type = configuration.getType())) {
				ret.put(type, new LinkedHashMap<String, Configuration>());
			}
			ret.get(type).put(configuration.getName(), configuration);
		}

		return ret;
	}

	public Configuration getByType(int type) {
		return configurationMapper.selectByType(type);
	}
}
