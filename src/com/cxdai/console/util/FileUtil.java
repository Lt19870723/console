package com.cxdai.console.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <p>
 * Description:文件操作工具类<br />
 * </p>
 * 
 * @title FileUtil.java
 * @package com.util.file
 * @author justin.xu
 * @version 0.1 2014年4月24日
 */
public class FileUtil {

	/** 保存图片的字节大小 */
	private final static int BUFFER_SIZE = 16 * 1024;

	/**
	 * 上传文件
	 * 
	 * @param src 文件对象
	 * @param location 保存的路径
	 * @param fileName 保存的文件名称
	 */
	public static void upload(MultipartFile src, HttpServletRequest request, String location, String fileName) {
		File localFile = new File(location + "/" + fileName);
		// 判断文件夹及文件是否存在
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		// 文件的绝对路径
		String tempPath = request.getRealPath("/") + "/" + location + "/" + fileName;
		File imageFile = new File(tempPath);
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = src.getInputStream();
				out = new BufferedOutputStream(new FileOutputStream(imageFile), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传文件加水印
	 * 
	 * @param src 文件对象
	 * @param location 保存的路径
	 * @param fileName 保存的文件名称
	 * @param iconPath 水印文件路径
	 * @throws Exception
	 */
	public static void uploadAddWatermark(MultipartFile src, HttpServletRequest request, String location, String fileName, String iconPath) throws Exception {
		File localFile = new File(location + "/" + fileName);
		// 判断文件夹及文件是否存在
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		// 文件的绝对路径
		String tempPath = request.getRealPath("/") + "/" + location + "/" + fileName;
		// 水印文件路径 ,如果没有，设置默认的
		if (null == iconPath || "".equals(iconPath.trim())) {
			iconPath = request.getRealPath("/") + "/images/watermark.png";
		} else {
			iconPath = request.getRealPath("/") + iconPath;
		}
		File imageFile = new File(tempPath);
		// 源文件输入流
		InputStream in = src.getInputStream();
		// 文件输出流
		FileOutputStream out = new FileOutputStream(imageFile);
		// 文件格式 ,比如说jpg
		String formatName = src.getOriginalFilename().substring(src.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
		// 图片源文件临时路径
		String imgsrc = ((DiskFileItem) ((CommonsMultipartFile) src).getFileItem()).getStoreLocation().getAbsolutePath();
		ImageMarkLogoByIcon.markImageByIcon(iconPath, in, imgsrc, out, -45, formatName);
	}

	/**
	 * 下载文件
	 * 
	 * @param path 访问的文件路径
	 * @param filename 下载时文件名称
	 * @param response
	 * @throws Exception
	 */
	public static void streamDownload(HttpServletRequest request, String path, String filename, HttpServletResponse response) throws Exception {
		File file = new File(request.getRealPath("/") + "/" + path);
		if (file.exists()) { // 判断该文件是否存在
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachments;filename=" + new String(filename.getBytes("GB2312"), "iso-8859-1"));
			response.addHeader("Coutent-Length", "" + file.length());
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath 文件全路径
	 * @throws Exception
	 */
	public static void deleteFile(HttpServletRequest request, String filePath) throws Exception {
		if (null != filePath) {
			// 文件的绝对路径
			String attachementPath = request.getRealPath("/") + "/" + filePath;
			File attachmentFile = new File(attachementPath);
			if (attachmentFile.exists()) { // 判断该文件是否存在
				attachmentFile.delete();
			}
		}
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * @Title: getPackagePathByWildcards
	 * @Description: 通过通配符查找文件夹或者文件所属的包名
	 * @param String wildcards 通配符
	 * @param String separator 分隔符
	 * @return String 根据分隔符得出包名
	 * @throws
	 */
	public static String getPackagePathByWildcards(String wildcards, String separator) {
		StringBuffer result = new StringBuffer();
		try {
			ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
			Resource[] source = resourceLoader.getResources(wildcards);
			for (int i = 0; i < source.length; i++) {
				Resource resource = source[i];
				String uri = resource.getURL().toString();
				String tmp = "";
				// 代表中jar包中扫描的结果
				if (uri.startsWith("jar:") || uri.startsWith("wsjar:")) {
					tmp = (uri.substring((uri.indexOf(".jar!") + 6), uri.length() - 1)).replace("/", ".");
				} else if (uri.startsWith("file:")) {// 代表从文件中扫描的结果
					tmp = (uri.substring((uri.indexOf("classes") + 8), uri.length() - 1)).replace("/", ".");
				}
				if (i == 0) {
					result.append(tmp);
				} else {
					result.append(separator);
					result.append(tmp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * <p>
	 * Description:验证文件格式和大小<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月24日
	 * @param file
	 * @param maxSize 以M为单位
	 * @param fileTypes
	 * @return String
	 */
	public static String validateFileInfo(MultipartFile file, Long maxSize, String[] fileTypes) {
		String result = "success";
		String filename = file.getOriginalFilename();
		if (file.isEmpty() || file.getSize() < 0 || null == filename || "".equals(filename)) {
			return "文件为空，请确认！";
		}
		// 判断文件大小
		Long fileSize = file.getSize();
		if (fileSize > maxSize * 1024 * 1024) {
			return "你所选择的的文件大于" + maxSize + "MB,请重新选择。";
		}
		// 判断文件类型
		if (filename.lastIndexOf(".") == -1) {
			return "文件格式不对，请重新选择！";
		}
		// 扩展名
		String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
		boolean haveInExt = false;
		for (String fileType : fileTypes) {
			if (extName.equals(fileType)) {
				haveInExt = true;
			}
		}
		if (!haveInExt) {
			return "文件格式不对，请重新选择！";
		}
		return result;
	}

	/**
	 * 将文本文件中的内容读入到buffer中
	 * 
	 * @param buffer buffer
	 * @param filePath 文件路径
	 * @throws IOException 异常
	 * @author cn.outofmemory
	 * @date 2013-1-7
	 */
	public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath 文件所在路径
	 * @return 文本内容
	 * @throws IOException 异常
	 * @author cn.outofmemory
	 * @date 2013-1-7
	 */
	public static String readFile(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		FileUtil.readToBuffer(sb, filePath);
		return sb.toString();
	}
}
