/**   
 * <p>
 * Description:文件上传<br />
 * </p>
 * @title FileLoad.java
 * @package com.cxdai.console.finance.virement.util 
 * @author tanghaitao
 * @version 0.1 2016年4月25日
 */
package com.cxdai.console.finance.virement.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.exception.AppException;
import com.cxdai.console.util.file.FileUtil;
import com.cxdai.console.util.file.ImageMarkLogoByIcon;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title FileLoad.java
 * @package com.cxdai.console.finance.virement.util 
 * @author tanghaitao
 * @version 0.1 2016年4月25日
 */

public class FileLoad {
	public static String ROOTUPLOADPATH = PropertiesUtil
			.getValue("www_cms_upload");//图片上传域名路径 
	public static String ROOTUPLOADPATH2 = PropertiesUtil
			.getValue("www_ss_upload");
	public static String ROOTUPLOADPATH3 = PropertiesUtil
			.getValue("ss_upload_path");

	public static char FENGE = '/';

	public static MessageBox upload(MultipartFile file, String cmsUploadPath,
			String fileDic, int fileSizeLimit) throws IOException {
		Date date = new Date(System.currentTimeMillis());
		String pixFilePath = cmsUploadPath + File.separator + fileDic;
		String pathFileDic = "";
		String pathDic = "";

		// 幻灯片，不添加日期目录；
		if (fileDic.equals(CmsConstant.SLIDESHOWFILEDIC1)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC2)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC3)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC4)) {
			pathFileDic = createUploadPath2(date, pixFilePath);
			pathDic = pixFilePath;
		} else {
			pathFileDic = createUploadPath(date, pixFilePath);
			pathDic = pixFilePath + File.separator + pathFileDic;
		}

		// 验证文件格式和大小是否正确
		String s = FileUtil.validateFileInfo(file, (long) fileSizeLimit,
				BusinessConstants.IMAGE_FILE_TYPE_LIST);
		if (!"success".equals(s)) {
			return MessageBox.build("1", s);
		}

		// 改变文件名
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf("."))
				.toLowerCase();

		String newFileName = getNewFileName(date);

		// 重命名文件
		newFileName += extName;

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(pathDic + File.separator + newFileName);
			out.write(file.getBytes());

		} catch (Exception e) {
			return MessageBox.build("1", e.getMessage());
		} finally {
			out.close();
		}

		String url2 = "";
		// 幻灯片
		if (fileDic.equals(CmsConstant.SLIDESHOWFILEDIC1)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC2)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC3)
				|| fileDic.equals(CmsConstant.SLIDESHOWFILEDIC4)) {
			url2 = ROOTUPLOADPATH2 + FENGE + fileDic + FENGE + newFileName;
		}
		else if (fileDic.equals(CmsConstant.WXMSGFILEDIC)) {
			url2 = PropertiesUtil.getValue("cms_upload_path") + FENGE + fileDic + FENGE + pathFileDic
					+ FENGE + newFileName;
		} else {
			url2 =ROOTUPLOADPATH3 + FENGE + fileDic + FENGE + pathFileDic
					+ FENGE + newFileName;
		}

		return MessageBox.build("0", url2);

	}

	private static String getNewFileName(Date date) {
		return DateUtils.getYear() + "" + DateUtils.getMonth()
				+ DateUtils.getDay() + DateUtils.getHour()
				+ DateUtils.getMinute() + DateUtils.getSecond();
	}

	private static String createUploadPath(Date date, String cmsUploadPath2) {
		String pathFileDic = DateUtils.getYear() + "-" + DateUtils.getMonth()
				+ "-" + DateUtils.getDay();
		String pathDic = cmsUploadPath2 + File.separator + pathFileDic;
		File file = new File(pathDic);

		if (!file.exists()) {
			file.mkdirs();
		}

		return pathFileDic;
	}

	// 路径不添加，年月日
	private static String createUploadPath2(Date date, String cmsUploadPath2) {

		String pathDic = cmsUploadPath2;
		File file = new File(pathDic);

		if (!file.exists()) {
			file.mkdirs();
		}

		return "";
	}
	public static Map<String, String> borrowUploadPic(MultipartFile file, HttpServletRequest request,String cmsUploadPath, int fileSizeLimit) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("result", "success");

		 
		Date date = new Date(System.currentTimeMillis());

		String pixFilePath = cmsUploadPath + FENGE + "portal";
		// 添加日期目录；
		String pathDic = pixFilePath;

		String newFileName = getNewFileName(date);
		// 解码文件名，当文件命中有中文等其他字符时
		String temp_path = java.net.URLDecoder.decode(file.getOriginalFilename(), "utf-8");

		if (temp_path.lastIndexOf('.') < 0) {
			resultMap.put("result", "上传文件格式不正确！");
			return resultMap;
		}

		if (file.getSize() > fileSizeLimit) {
			resultMap.put("result", "上传文件过大！");
			return resultMap;
		}
		// 重命名文件
		newFileName += temp_path.substring(temp_path.lastIndexOf('.'));
		FileOutputStream out = null;
		try {
			String ss = pathDic + FENGE + newFileName;
			System.out.println("上传地址==================" + ss);
			out = new FileOutputStream(ss);
			out.write(file.getBytes());
			// 给图片添加水印,水印旋转-45
		    ImageMarkLogoByIcon.markImageByIcon(request.getServletContext().getRealPath("/") + "images/watermark.png", ss, ss, -45, "JPG");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("上传失败！");
		} finally {
			out.close();
		}
		String url = "";
		// 幻灯片
		url = ROOTUPLOADPATH2 + FENGE + "portal" + FENGE + newFileName;
		System.out.println("上传地址url==================" + url);
		resultMap.put("url", url);
		resultMap.put("path", pathDic + File.separator + newFileName);
		System.out.println("上传地址path==================" + pathDic + FENGE + newFileName);
		resultMap.put("fileName", newFileName);
		return resultMap;

	}
}
