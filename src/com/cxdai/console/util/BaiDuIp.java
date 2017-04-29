package com.cxdai.console.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Description:调用百度api获取ip城市方法<br />
 * </p>
 * 
 * @title BaiDuIp.java
 * @package com.util
 * @author justin.xu
 * @version 0.1 2014年11月14日
 */
public class BaiDuIp {
	private static Logger logger = LoggerFactory.getLogger(BaiDuIp.class);

	/**
	 * <p>
	 * Description:根据ip获取省份、城市、地区方法<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param ip
	 * @return Map 返回以 province,city,area 健
	 */
	public static final IpAddr queryAddrByIp(String ip) {
		String url = new StringBuffer(112).append("http://opendata.baidu.com/api.php?resource_id=6006&ie=utf8&oe=gbk&t=").append(System.currentTimeMillis()).append("&query=").append(ip).toString();
		try {
			String result = getResponseFromServer(url, "GBK");
			// String result = HttpURLUtil.doPost(url, null);
			if (result != null) {

				Baidu baidu = JsonUtils.json2Bean(result, Baidu.class);
				if (baidu != null) {
					return baidu.getIpAddr();
				}
			}
		} catch (Exception e) {
			logger.error("get addr. by ip from baidu api..", e);
		}
		return IpAddr.DEFAULT;
	}

	public static String getResponseFromServer(String url, String encoding) {
		try {
			return getResponseFromServer(new URL(url), encoding);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String getResponseFromServer(URL constructedUrl, String encoding) {
		return getResponseFromServer(constructedUrl, HttpsURLConnection.getDefaultHostnameVerifier(), encoding);
	}

	public static String getResponseFromServer(URL constructedUrl, HostnameVerifier hostnameVerifier, String encoding) {
		URLConnection conn = null;
		try {
			conn = constructedUrl.openConnection();
			if ((conn instanceof HttpsURLConnection)) {
				((HttpsURLConnection) conn).setHostnameVerifier(hostnameVerifier);
			}
			BufferedReader in;
			if ((encoding == null) || (encoding.length() == 0)) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
			}

			StringBuilder stringBuffer = new StringBuilder(255);
			String line;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if ((conn != null) && ((conn instanceof HttpURLConnection))) {
				((HttpURLConnection) conn).disconnect();
			}
		}
	}

	static class Baidu {
		private String status;
		private String t;
		private List<Data> data = new ArrayList<Data>(0);

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getT() {
			return t;
		}

		public void setT(String t) {
			this.t = t;
		}

		public List<Data> getData() {
			return data;
		}

		public void setData(List<Data> data) {
			this.data = data;
		}

		public IpAddr getIpAddr() {

			if (!data.isEmpty()) {
				String location = data.get(0).getLocation();
				if (location != null && location.length() > 0) {

					String[] locationArr = location.split(" ");
					String province = location;
					String city = location;
					String area = location;

					if (locationArr.length > 1) {
						String provinceAndCity = locationArr[0];
						if (provinceAndCity.contains("省")) {
							province = provinceAndCity.substring(0, provinceAndCity.indexOf("省") + 1);
							if (provinceAndCity.contains("市")) {
								city = provinceAndCity.substring(provinceAndCity.indexOf("省") + 1);
							} else {
								city = locationArr[1];
							}
						} else if (provinceAndCity.contains("市")) {
							province = provinceAndCity.substring(0, provinceAndCity.indexOf("市") + 1);
							city = provinceAndCity.substring(0, provinceAndCity.indexOf("市") + 1);
						} else {
							province = provinceAndCity;
							city = provinceAndCity;
						}
						area = locationArr[1];
					}

					IpAddr ipAddr = new IpAddr();
					ipAddr.setArea(area);
					ipAddr.setProvince(province);
					ipAddr.setCity(city);
					return ipAddr;
				}
			}
			return IpAddr.DEFAULT;
		}
	}

	static class Data {
		private String location;
		private String titlecont;
		private String origip;
		private String origipquery;
		private String showlamp;
		private Integer showLikeShare;
		private Integer shareImage;
		@JsonProperty("ExtendedLocation")
		private String extendedLocation;
		@JsonProperty("OriginQuery")
		private String originQuery;
		private String tplt;
		private String resourceid;
		private String fetchkey;
		private String appinfo;
		@JsonProperty("role_id")
		private Integer roleId;
		@JsonProperty("disp_type")
		private Integer dispType;

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getTitlecont() {
			return titlecont;
		}

		public void setTitlecont(String titlecont) {
			this.titlecont = titlecont;
		}

		public String getOrigip() {
			return origip;
		}

		public void setOrigip(String origip) {
			this.origip = origip;
		}

		public String getOrigipquery() {
			return origipquery;
		}

		public void setOrigipquery(String origipquery) {
			this.origipquery = origipquery;
		}

		public String getShowlamp() {
			return showlamp;
		}

		public void setShowlamp(String showlamp) {
			this.showlamp = showlamp;
		}

		public Integer getShowLikeShare() {
			return showLikeShare;
		}

		public void setShowLikeShare(Integer showLikeShare) {
			this.showLikeShare = showLikeShare;
		}

		public Integer getShareImage() {
			return shareImage;
		}

		public void setShareImage(Integer shareImage) {
			this.shareImage = shareImage;
		}

		public String getExtendedLocation() {
			return extendedLocation;
		}

		public void setExtendedLocation(String extendedLocation) {
			this.extendedLocation = extendedLocation;
		}

		public String getOriginQuery() {
			return originQuery;
		}

		public void setOriginQuery(String originQuery) {
			this.originQuery = originQuery;
		}

		public String getTplt() {
			return tplt;
		}

		public void setTplt(String tplt) {
			this.tplt = tplt;
		}

		public String getResourceid() {
			return resourceid;
		}

		public void setResourceid(String resourceid) {
			this.resourceid = resourceid;
		}

		public String getFetchkey() {
			return fetchkey;
		}

		public void setFetchkey(String fetchkey) {
			this.fetchkey = fetchkey;
		}

		public String getAppinfo() {
			return appinfo;
		}

		public void setAppinfo(String appinfo) {
			this.appinfo = appinfo;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}

		public Integer getDispType() {
			return dispType;
		}

		public void setDispType(Integer dispType) {
			this.dispType = dispType;
		}
	}

	public static class IpAddr {
		private static final IpAddr DEFAULT = new IpAddr();

		private String area;
		private String province;
		private String city;

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
	}
}