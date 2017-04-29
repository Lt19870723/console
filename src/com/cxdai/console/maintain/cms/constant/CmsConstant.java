package com.cxdai.console.maintain.cms.constant;

public interface CmsConstant {
	/**
	 * @fields 文件大小不能超过1M
	 */
	public final static int fileSizeLimit = 512000 * 2;

	/**
	 * 存放文章图片的目录名称
	 * 
	 * @fields ARTICLEFILEDIC
	 */
	String ARTICLEFILEDIC = "Article";

	String WXMSGFILEDIC = "wxmsg";

	/**
	 * 存放幻灯图片的目录名称 | 官网
	 * 
	 * @fields SLIDESHOWFILEDIC
	 */
	String SLIDESHOWFILEDIC1 = "portal";
	String SLIDESHOWFILEDIC2 = "zixun";
	String SLIDESHOWFILEDIC3 = "bbs";
	String SLIDESHOWFILEDIC4 = "wx";

	/**
	 * 存放栏目图片的目录名称
	 * 
	 * @fields CHANNELFILEDIC
	 */
	String CHANNELFILEDIC = "Channel";

	/**
	 * 存放词条图片的目录名称
	 * 
	 * @fields PEDIAENTRYFILEDIC
	 */
	String PEDIAENTRYFILEDIC = "Pediaentry";

	int CMSADD = 1;
	int CMSUPDATE = 2;
	int CMSDELETE = 3;
	int CMSTOP = 4;
	int CMSHIDDEN = 5;
	int CHANNELSOURCETYPE = 1;
	int ARTICLESOURCETYPE = 2;
	int TAGSOURCETYPE = 3;
	int COMMONSSOURCETYPE = 4;
	int PEDIAENTRYSOURCETYPE = 5;

	/**
	 * 幻灯图片操作数据 |
	 */
	int SLIDESHOWADD = 1;
	int SLIDESHOWUPDATE = 2;
	int SLIDESHOWDELETE = 3;

	int PORTALTYPE = 1;
	int ZIXUNTYPE = 2;
	int BBSTYPE = 3;

}
