package com.cxdai.console.common;

import java.io.File;

public class WxBase {
	public static String WX_URL = "http://test.gcjr.com";
	public static String IMAGE_URL = "http://testpay.gcjr.com";
	public static String WX_IMAGE_PATH = "rootwx/pushmessagesimages/";

	static {
		File dir = new File(WX_IMAGE_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
}
