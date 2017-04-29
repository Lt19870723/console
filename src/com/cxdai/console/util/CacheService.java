package com.cxdai.console.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.exceptions.JedisException;


/**  
 * 
 * @Description:  Redis服务工具类
 * @Author:       bb
 * @CreateDate:   2015年11月10日
 * @Version:      1.0
 *   
 */ 
public class CacheService {
	public static Logger log = Logger.getLogger(CacheService.class);
	
	private String CACHE_CONFIG_FILE = "cacheConfig.properties"; 
	
	private static String REDIS_IP= "redis.ip.";
	
	private static String REDIS_PORT= "redis.port.";
	
	private static String JEDIS_POOL_MAXACTIVE= "jedis.pool.maxActive";
	
	private static String JEDIS_POOL_MAXIDLE= "jedis.pool.maxIdle";
	
	private static String JEDIS_POOL_MAXWAIT= "jedis.pool.maxWait";
	
	private static String JEDIS_POOL_TESTONBORROW= "jedis.pool.testOnBorrow";

	private static String JEDIS_POOL_TESTONRETURN= "jedis.pool.testOnReturn";
	
	private static String JEDIS_TIMEOUT= "jedis.timeOut";
	
	private static String JEDIS_RETRYNUM= "jedis.retryNum";
	
	private static String JEDIS_SERVER_NUM= "jedis.server.num";
	
	private static String JEDIS_SERVER_PWD= "jedis.server.pwd";
	
	private static ShardedJedisPool pool;
	
	private static CompositeConfiguration pConfig;
	
	/**
	 * 获取属性配置数据.
	 * 
	 * @return 属性配置实例
	 */
	private CompositeConfiguration getConfig(){
		if(pConfig == null){
			pConfig = PropertyUtil.getConfig(CACHE_CONFIG_FILE);
		}
		return pConfig;
	}

	/**
	 * 获取连接池.
	 * 
	 * @return 连接池实例
	 */
	private ShardedJedisPool getPool() {
		if (pool == null) {
			try {				
				JedisPoolConfig config = new JedisPoolConfig();
				
				// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
				// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
				config.setMaxIdle(getConfig().getInt(JEDIS_POOL_MAXACTIVE));
				// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
				config.setMaxIdle(getConfig().getInt(JEDIS_POOL_MAXIDLE));
				// 当池内没有返回对象时，最大等待时间
				config.setMaxWait(getConfig().getInt(JEDIS_POOL_MAXWAIT));
				// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
				config.setTestOnBorrow(getConfig().getBoolean(JEDIS_POOL_TESTONBORROW));
				// 当调用return Object方法时，是否进行有效性检查
				config.setTestOnReturn(getConfig().getBoolean(JEDIS_POOL_TESTONRETURN));
				// config.setWhenExhaustedAction(WhenExhaustedAction.);
				/**
				 * 如果你遇到 java.net.SocketTimeoutException: Read timed out
				 * exception的异常信息 请尝试在构造JedisPool的时候设置自己的超时值.
				 * JedisPool默认的超时时间是2秒(单位毫秒)
				 */
				JedisShardInfo jedisShardInfo;
				int serverNum = getConfig().getInt(JEDIS_SERVER_NUM);
				List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
				//连接多台主机服务
				if(serverNum > 0){
					String redisIp;
					int redisPort;					
					for(int i =1; i <= serverNum; i++){
						redisIp = getConfig().getString(REDIS_IP+i);
						redisPort = getConfig().getInt(REDIS_PORT+i);
						jedisShardInfo = new JedisShardInfo(redisIp,
								redisPort, getConfig().getInt(JEDIS_TIMEOUT));
						jedisShardInfo.setPassword(getConfig().getString(JEDIS_SERVER_PWD));
						list.add(jedisShardInfo);
					}					
				}				
				// 根据配置文件,创建shared池实例
				pool = new ShardedJedisPool(config, list);
			} catch (Exception e) {
				log.error("获取jedis连接池信息错误： " + e);
			}
		}
		return pool;
	}

	/**
	 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
	 */
	private static class CacheServiceHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static CacheService instance = new CacheService();
	}

	/**
	 * 当getInstance方法第一次被调用的时候，它第一次读取
	 * CacheServiceHolder.instance，导致CacheServiceHolder类得到初始化
	 * ；而这个类在装载并被初始化的时候，会初始化它的静
	 * 态域，从而创建CacheService的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
	 * 这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
	 */
	public static CacheService getInstance() {
		return CacheServiceHolder.instance;
	}

	/**
	 * 获取Cache实例.
	 * 
	 * @return Cache工具类实例
	 */
	private ShardedJedis getCache() {
		ShardedJedis jedis = null;
		int count = 0;
		do {
			try {
				jedis = getPool().getResource();
			} catch (JedisException e) {
				log.error("failed:getCache ", e);
				// 销毁对象
				if (jedis != null) {
					getPool().returnBrokenResource(jedis);
				}
			}
			count++;
		} while (jedis == null
				&& count < getConfig().getInt(JEDIS_RETRYNUM));
		return jedis;
	}

	/**
	 * 释放redis实例到连接池.
	 * 
	 * @param Redis实例
	 * @param isBroken实例是否出错
	 */
	private void release(ShardedJedis jedis, boolean isBroken) {
		if (jedis != null) {
			if (isBroken) {
				getPool().returnBrokenResource(jedis);
			} else {
				getPool().returnResource(jedis);
			}
		}
	}

	/**
	   * 　保存数据，获取原值，更新为新值
	 * @param key
	 * @param value
	 * @return 上一次保存的value
	 */
	public void put(String key, String value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			jedis.set(key, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:put String ", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	/**
	   * 　保存数据，获取原值，更新为新值
	 * @param key
	 * @param value
	 */
	public void put(byte[] key, byte[] value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getCache();
			jedis.set(key, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:put byte[] ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 保存map集合数据
	 * @param key key值
	 * @param map map对像
	 */
	public void put(final String key, final Map<String, String> map) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			jedis.pipelined(new ShardedJedisPipeline() {
				@Override
				public void execute() {
					for (Map.Entry<String, String> element : map.entrySet()) {
						hset(key, element.getKey(), element.getValue());
					}
				}
			});
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:put map ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 保存List集合数据
	 * @param key key值
	 * @param list list对像
	 */
	public void put(final String key, final List<String> list) {
		if (list != null && list.size() > 0) {
			ShardedJedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getCache();
				// jedis.select(getDBIndex());
				if (jedis.exists(key)) {
					jedis.del(key);
				}
				jedis.pipelined(new ShardedJedisPipeline() {
					@Override
					public void execute() {
						for (String aList : list) {
							//在名称为key的list尾添加一个值为value的元素 
							rpush(key, aList);
						}
					}
				});
			} catch (Exception e) {
				isBroken = true;
				log.error("failed:put List ", e);
			} finally {
				release(jedis, isBroken);
			}
		}
	}
	
	/**
	 * 保存set集合数据
	 * @param key key值
	 * @param set set对像
	 */
	public void put(final String key, final Set<String> set) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			jedis.pipelined(new ShardedJedisPipeline() {
				@Override
				public void execute() {
					for (String s : set) {
						sadd(key, s);
					}
				}
			});
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:put set ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	
	
	/**
	 * 在名称为key的list尾添加一个值为value的元素
	 * @param key key值
	 * @param value value值
	 */
	public void putList(String key, String value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			jedis.rpush(key, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:putList ", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 在名称为key的set尾添加一个值为value的元素
	 * @param key key值
	 * @param value value值
	 */
	public void putSet(String key, String value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			jedis.sadd(key, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:putSet ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 根据map向名称为key的hash中添加元素field 
	 * @param key key值
	 * @param mapKey 需添加的map的kay值
	 * @param mapValue 需添加的map的value值
	 */
	public void putMap(String key, String mapKey, String mapValue) {
		boolean isBroken = false;
		ShardedJedis jedis = null;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			//将名称为key的hash中field的value增加integer 
			//jedis.hincrBy(key, mapKey, 1);
			jedis.hset(key, mapKey, mapValue);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:putMap ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 返回数据库中名称为key的string的value
	 * @param key
	 * @return value
	 */
	public String getString(String key) {
		String value = null;
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = org.apache.commons.lang.StringUtils.isNotBlank(value)
						&& !"null".equalsIgnoreCase(value) ? value : null;
			}
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getString ", e);
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}

	/**
	 * 返回数据库中名称为key的byte[]的data
	 * @param key
	 * @return data
	 */
	public byte[] getBytes( byte[] key) {
		 byte[] data = null;
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				data = jedis.get(key);
			}
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getString ", e);
		} finally {
			release(jedis, isBroken);
		}
		return data;
	}

	/**
	 * 返回名称为key的list元素 
	 * @param key
	 * @return list<String>
	 */
	public List<String> getList(String key) {
		List<String> list = null;
		boolean isBroken = false;
		ShardedJedis jedis = null;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				list = jedis.lrange(key, 0, -1);
			}
		} catch (JedisException e) {
			isBroken = true;
			log.error("failed:getList ", e);
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 返回名称为key的set元素
	 * @param key
	 * @return set<String>
	 */
	public Set<String> getSet(String key) {
		Set<String> set = null;
		boolean isBroken = false;
		ShardedJedis jedis = null;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			if (jedis.exists(key)) {
				set = jedis.smembers(key);
			}
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getSet ", e);
		} finally {
			release(jedis, isBroken);
		}
		return set;
	}

	/**
	 * 返回名称为key的hash中所有的键（field）及其对应的value
	 * @param key
	 * @return Map<String, String>
	 */
	public Map<String, String> getMap(String key) {
		Map<String, String> hashMap = null;
		boolean isBroken = false;
		ShardedJedis jedis = null;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			hashMap = jedis.hgetAll(key);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getMap ", e);
		} finally {
			release(jedis, isBroken);
		}
		return hashMap;
	}

	/**
	 * 返回名称为key的hash中field对应的value 
	 * @param key
	 * @param mapKey 字段值
	 * @return 对应的 value
	 */
	public String getMapValue(String key, String mapKey) {
		String value = null;
		boolean isBroken = false;
		ShardedJedis jedis = null;
		try {
			jedis = this.getCache();
			if (jedis != null) {
				// jedis.select(getDBIndex());
				if (jedis.exists(key)) {
					value = jedis.hget(key, mapKey);
				}
			}
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getMapValue ", e);
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}
	
	/**
	 * 删除名称为key的set中的元素
	 * @param key
	 * @param value
	 */
	public void removeSet(String key, String value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			jedis.srem(key, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:removeSet ", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 删除名称为key的map中的元素
	 * @param key key值
	 * @param mapKey 需删除MAP的key值
	 */
	public void removeMap(String key, String mapKey) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			jedis.hdel(key, mapKey);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:removeMap ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 删除值为value的list中的元素
	 * 
	 * @param key key值
	 * @param value 需删除的元素
	 */
	public void removeList(String key, String value) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			//删除count个key的list中值为values的元素
			jedis.lrem(key, 0, value);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:removeList ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 删除一个key 
	 * 
	 * @param key
	 * @return 删除状态：1成功0失败
	 */
	public Long remove(String key) {
		boolean isBroken = false;
		ShardedJedis jedis = null;
		long result = 0;
		try {
			jedis = getCache();
			// jedis.select(getDBIndex());
			return jedis.del(key);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:delKeyFromJedis ", e);
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 确认一个key是否存在 
	 * @param key
	 * @return key存在为true,不存在为false
	 */
	public boolean existKey(String key) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			return jedis.exists(key);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:existKey ", e);
		} finally {
			release(jedis, isBroken);
		}
		return false;
	}
		
	/**
	 * 确认一个key是否存在 
	 * @param key
	 * @return key存在为true,不存在为false
	 */
	public long ttl(String key) {
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			// jedis.select(getDBIndex());
			return jedis.ttl(key);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:existKey ", e);
		} finally {
			release(jedis, isBroken);
		}
		return -100;
	}
	
	/**
	 * 判断为key的set集合中是否已存在value
	 * @param key
	 * @param value
	 * @return boolean,存在返回true，不存在返回false
	 */
	public boolean existValueOfSetByKey(String key,String value){
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			return jedis.sismember(key, value);		
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:existValueOfSetByKey ", e);
		} finally {
			release(jedis, isBroken);
		}
		return false;
	}
	
	/**
	 * @param jedisKey
	 * @param setKey
	 * @param mapKey
	 * @param mapValue
	 * 用兩個redis的key-value保存結果
	 * 第一個：jedisKey：set<setKey>
	 * 第二個：setKey:Map<mapKey,mapValue>
	 */
	public void putKeySetAndValueMap(String jedisKey,String setKey,String mapKey,String mapValue){
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
			this.putSet(jedisKey, setKey);
			this.putMap(setKey, mapKey, mapValue);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:putKeySetAndValueMap ", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 
	 * @param jedisKey
	 * 取用兩個redis的key-value保存的結果
	 * 第一個：jedisKey：set<setKey>
	 * 第二個：setKey:Map<mapKey,mapValue>
	 */
	public void getSetKeyAndMapValue(String jedisKey){
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
            Set set = this.getSet(jedisKey);//未排序
            if(set!=null && set.size()>0){
            	String jedisMapKey = null;
            	Map<String,String> map = null;
            	for(Iterator<String> it=set.iterator();it.hasNext();){
            		jedisMapKey = it.next();
            		map = this.getMap(jedisMapKey);            		
    				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    				Calendar calendar = Calendar.getInstance();
    				calendar.setTimeInMillis(Long.parseLong(jedisMapKey));
    				String datestring = format.format(calendar.getTime());
            		if(map!=null && map.size()>0){
            			for(Entry<String,String> entry:map.entrySet()){
            				System.out.println("jedisKey:"+jedisKey+",jedisMapKey:"+datestring+",mapKey:"+entry.getKey()+",mapValue:"+entry.getValue());
            			}
            		}
            	}
            }
		}catch (NumberFormatException e) {
			isBroken = true;
			log.error("failed:getSetKeyAndMapValue NumberFormatException ", e);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getSetKeyAndMapValue ", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	
	/**
	 * 
	 * @param jedisKey
	 * 取用兩個redis的key-value保存的結果
	 * 第一個：jedisKey：set<setKey>
	 * 第二個：setKey:Map<mapKey,mapValue>
	 */
	public void getSortSetKeyAndMapValue(String jedisKey){
		ShardedJedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getCache();
            List<String> list = jedis.sort(jedisKey);//排序
            if(list!=null && list.size()>0){
            	String jedisMapKey = null;
            	Map<String,String> map = null;
            	for(Iterator<String> it=list.iterator();it.hasNext();){
            		jedisMapKey = it.next();
            		map = this.getMap(jedisMapKey);            		
    				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    				Calendar calendar = Calendar.getInstance();
    				calendar.setTimeInMillis(Long.parseLong(jedisMapKey));
    				String datestring = format.format(calendar.getTime());
            		if(map!=null && map.size()>0){
            			for(Entry<String,String> entry:map.entrySet()){
            				System.out.println("jedisKey:"+jedisKey+",jedisMapKey:"+datestring+",mapKey:"+entry.getKey()+",mapValue:"+entry.getValue());
            			}
            		}
            	}
            }
		}catch (NumberFormatException e) {
			isBroken = true;
			log.error("failed:getSetKeyAndMapValue NumberFormatException ", e);
		} catch (Exception e) {
			isBroken = true;
			log.error("failed:getSetKeyAndMapValue ", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 获取类型信息
	 * @Description：
	 * @param key
	 * @return: 
	 * @return String:
	 */
    public String type(String key) {    	
    	ShardedJedis jedis = null;
		boolean isBroken = false;
        String result = null;
        try {
        	jedis = getCache();
            result = jedis.type(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }

    /**
     * 在某段时间后失效
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }
    
    /**
     * 在某段时间后失效
     */
    public Long expire(byte[] key, int seconds) {
        Long result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     */
    public Long expireAt(String key, long time) {
        Long result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.expireAt(key, time);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }
    
    public String substr(String key, int start, int end) {
        String result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.substr(key, start, end);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }

    /**
     * list排序
     * @Description：
     * @param key
     * @return: 
     * @return List<String>:
     */
    public List<String> sort(String key) {
        List<String> result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.sort(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }
    
    /**
     * list根据条件排序
     * @Description：
     * @param key
     * @param sortingParameters
     * @return: 
     * @return List<String>:
     */
    public List<String> sort(String key, SortingParams sortingParameters) {
        List<String> result = null;
        ShardedJedis jedis = null;
		boolean isBroken = false;
        try {
        	jedis = getCache();
            result = jedis.sort(key, sortingParameters);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            isBroken = true;
        } finally {
        	release(jedis, isBroken);
        }
        return result;
    }

    //test method
  	public void test(){
  		ShardedJedis jedis = null;
  		boolean isBroken = false;
  		try {
  			jedis = this.getCache();
  			// 添加数据  
  			jedis.sadd("sets", "HashSet");  
  			jedis.sadd("sets", "SortedSet");  
  			jedis.sadd("sets", "TreeSet");  
  			jedis.sadd("sets", "TreeSet");  
  			// 判断value是否在列表中  
  			System.out.println(jedis.sismember("sets", "HashSet"));  
  			System.out.println(jedis.sismember("sets", "SortedSet"));  
  			System.out.println(jedis.sismember("sets", "TreeSet"));  
  			System.out.println(CacheService.getInstance().getSet("sets"));  
  		} catch (Exception e) {
  			isBroken = true;
  			log.error("test ", e);
  		} finally {
  			release(jedis, isBroken);
  		}
  	}
  	
  	 /**
     * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
     *
     * @param String
     *            key
     * @param String
     *            fieid 存储位置
     * @param String
     *            long value 要增加的值,可以是负数
     * @return 增加指定数字后，存储位置的值
     * */
    public int hincrby(String key, String fieid, long value) {
       boolean isBroken = false;
    		ShardedJedis jedis = null;
    		int s=0;
    		try {
    			jedis = this.getCache();
    			Long sl = jedis.hincrBy(key, fieid, value);
    			s=sl.intValue();
    		} catch (Exception e) {
    			isBroken = true;
    			log.error("failed:putMap ", e);
    		} finally {
    			release(jedis, isBroken);
    		}
			return s;
    }
    
    
 
    
    
	 public static void main(String[] args) {  
		 	List<String> l = new ArrayList<String>();
		 	l.add("a");
		 	l.add("b");
		 	l.add("c");
		 	Map<String,String> m = new HashMap<String,String>();
		 	m.put("x", "x");
		 	m.put("y", "y");
		 	m.put("z", "z");
		 	Set<String> s = new HashSet<String>();
		 	s.add("1");
		 	s.add("22");
		 	s.add("3");
		 	
		 	

		 	CacheService.getInstance().put("a1", "abc");
//		 	CacheService.getInstance().put("list", l);
//		 	CacheService.getInstance().put("map", m);
//		 	CacheService.getInstance().put("set", s);
		 	//CacheService.getInstance().remove("a1");
		 	//CacheService.getInstance().removeList("list", "a");
		 	//CacheService.getInstance().removeMap("map", "x");
		 	//CacheService.getInstance().removeSet("set", "1");
//		 	CacheService.getInstance().putList("list", "aa");
//		 	CacheService.getInstance().putMap("map", "xx", "xx");
//		 	CacheService.getInstance().putSet("set", "5");
		 	System.out.println(CacheService.getInstance().getString("a1"));
//		 	System.out.println(CacheService.getInstance().getList("list"));
//		 	System.out.println(CacheService.getInstance().getMap("map"));
//		 	System.out.println(CacheService.getInstance().getMapValue("map", "xx"));
//		 	System.out.println(CacheService.getInstance().getSet("set"));
//		 	CacheService.getInstance().test();
//		 	System.out.println(CacheService.getInstance().existValueOfSetByKey("sets", "SortedSet"));
//		 	System.out.println(CacheService.getInstance().existValueOfSetByKey("sets", "TreeSet"));
//		 	System.out.println(CacheService.getInstance().existValueOfSetByKey("sets", "HashSet"));
//		 	
//		 	CacheService.getInstance().putSet("set.key", "5");
//		 	boolean flag = CacheService.getInstance().existValueOfSetByKey("set.key","6");
//		 	System.out.println(CacheService.getInstance().getSet("set.key"));
//		 	if(!flag){
//		 		CacheService.getInstance().putSet("set.key", "6");
//		 		System.out.println(CacheService.getInstance().getSet("set.key"));
//		 	}
//		 	
//		 	CacheService.getInstance().putMap("map.key", "mapKey", "count");
//		 	System.out.println(CacheService.getInstance().getMap("map.key"));
//		 	
//		 	System.out.println("======================================================");
//		 	Map<String, String> map = CacheService.getInstance().getMap("channel_url_key_" + "0020AVHU");
//		 	 for (String key : map.keySet()) {
//		 		   System.out.println("key= "+ key + " and value= " + map.get(key));
//		 		  }
//		 	CacheService.getInstance().remove("channel_url_key_" + "0020AVHU");
//		 	CacheService.getInstance().getSortSetKeyAndMapValue("activity_count_key");

	    } 

}