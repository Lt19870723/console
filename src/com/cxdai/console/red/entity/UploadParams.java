package com.cxdai.console.red.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * <p>
 * Description:上传文件的相关参数类：
 * </p>
 * @title UploadParams.java
 * @package com.common.upload.entity
 * @author ZHUCHEN
 * @version 0.1 2014年8月14日
 */
public class UploadParams implements Serializable {
	/**
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 5928281370896970546L;

	private ArrayList<UploadFileInfo> files = new ArrayList<UploadFileInfo>();// 用于存放批量上传的文件信息
	private UploadFileInfo file;// 用于存放单个文件的文件信息
	private static String uploadImageRoot;// 上传图片的根路径
	private static int uploadsAvailable;// 批量上传的文件数量
	private static boolean autoUpload;// 自动上传
	private static boolean useFlash;// 显示flash插件
	private static String defaultHeadImage;// 上传图片的根路径
	private static String uploadExcelRoot;

	private Long time;

	static {
		setProperties();
	}

	/**
	 * <p>
	 * Description:初始化上传参数
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2014年8月18日 void
	 */
	public static void setProperties() {
		Properties p = new Properties();
		try {
			p.load(UploadParams.class.getResourceAsStream("/allproperties/upload.properties"));
			uploadImageRoot = p.getProperty("uploadImageRoot");
			uploadsAvailable = Integer.parseInt(p.getProperty("uploadsAvailable"));
			autoUpload = Boolean.valueOf(p.getProperty("autoUpload"));
			useFlash = Boolean.valueOf(p.getProperty("useFlash"));
			defaultHeadImage = p.getProperty("defaultHeadImage");
			uploadExcelRoot = p.getProperty("uploadExcelRoot");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUploadImageRoot() {
		return uploadImageRoot;
	}

	public void setUploadImageRoot(String uploadImageRoot) {
		this.uploadImageRoot = uploadImageRoot;
	}

	public ArrayList<UploadFileInfo> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadFileInfo> files) {
		this.files = files;
	}

	public UploadFileInfo getFile() {
		return file;
	}

	public void setFile(UploadFileInfo file) {
		this.file = file;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public Long getTime() {
		return new Date().getTime();
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public static String getDefaultHeadImage() {
		return defaultHeadImage;
	}

	public static void setDefaultHeadImage(String defaultHeadImage) {
		UploadParams.defaultHeadImage = defaultHeadImage;
	}

	public static String getUploadExcelRoot() {
		return uploadExcelRoot;
	}

	public static void setUploadExcelRoot(String uploadExcelRoot) {
		UploadParams.uploadExcelRoot = uploadExcelRoot;
	}

}
