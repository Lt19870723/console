package com.cxdai.console.common.statics;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>
 * Description:业务常量类<br />
 * </p>
 * 
 * @title BusinessConstants.java
 * @package com.cxdai.console.statics
 * @author justin.xu
 * @version 0.1 2014年4月18日
 */
public interface BusinessConstants {

	/********************* 实名认证增加信用积分 20分 begin *************************/
	public Integer REALNAME_APPRO_CREDIT_INTEGRAL = 20;
	/** 实名认证增加信用积分 20分 end */

	/********************* 认证扣款开始 *****************************************/
	public BigDecimal VIP_MONEY = new BigDecimal(10);
	public BigDecimal APPRO_PASS_MONEY = new BigDecimal(10);
	/********************* 认证扣款结束 *****************************************/

	/****/
	/** 格式化数字,保留两位小数，以，进行分隔符千位 */
	public static DecimalFormat decimalDf = new DecimalFormat("#,#00.00");
	public static DecimalFormat decimalPercentDf = new DecimalFormat("#,##0.00");
	/** 格式化数字,保留两位小数，以，进行分隔符千位 */
	public static DecimalFormat numberDf = new DecimalFormat("#,##0");
	/********************* 锁定记录 ****************************/
	public static String LOCKED_RECORD_YES = "yes";
	/******************** 可以可几个月申请提前解锁 **********************************/
	public Integer FIRST_UNLOCK_AFTER_MONTH = 6;

	/*********** 默认分页条数 *****************************/

	public Integer DEFAULT_PAGE_SIZE = 30;

	/********************** 操作成功 ********************************************/
	public String SUCCESS = "success";

	/********************* 每笔提现手续费开始 ***************************************/
	public BigDecimal TAKE_MONEY_FEE = new BigDecimal("2");
	/********************* 每笔提现手续费 结束 ***************************************/

	/*********************** 在线支付相关信息 开始 ***************************************/
	public static String ONLINE_PAY_CHINABANK_INVALID_SHOPNO = "22791329"; // 公司在网银在线平台开通的商户号
																			// 老账号,已失效
	// public static String ONLINE_PAY_CHINABANK_MD5KEY =
	// "guochengjinrong22791329";//公司在网银在线平台设置的MD5
	public static String ONLINE_PAY_CHINABANK_SHOPNO = "23234639"; // 公司在网银在线平台开通的商户号

	/************************ 第三方支付平台设置 开始 *****************************************/
	public static int ONLINE_TYPE_CHINABANK = 1;// 网银在线
	public static String ONLINE_TYPE_CHINABANK_NAME = "网银在线";// 网银在线

	public static int ONLINE_TYPE_GOPAY = 2;// 国付宝
	public static String ONLINE_TYPE_GOPAY_NAME = "国付宝";// 国付宝

	public static int ONLINE_TYPE_SHENGPAY = 3;// 盛付通
	public static String ONLINE_TYPE_SHENGPAY_NAME = "盛付通";// 盛付通

	public static int ONLINE_TYPE_SINAPAY = 4;// 新浪支付
	public static String ONLINE_TYPE_SINAPAY_NAME = "新浪支付";// 新浪支付

	/** 认证修改记录日志 修改类型（1、邮箱 2、手机 3、实名认证） begin */
	public Integer APPRO_MODIFY_LOG_TYPE_EMAIL = 1;
	public Integer APPRO_MODIFY_LOG_TYPE_MOBILE = 2;
	public Integer APPRO_MODIFY_LOG_TYPE_REALNAME = 3;
	/** 认证修改记录日志 修改类型（1、邮箱 2、手机 3、实名认证） end */

	/*****************************************************************************************
	 * 净值额度表 类型【0：借款入账，1：网站奖励,2:还款扣除，3：还款入帐 4：垫付入帐 5：垫付后还款扣除 6：垫付还款后非VIP收取利息
	 * 7:提前还款扣除 8：提前还款入帐 9：收取罚息:10:直通车解锁 11:现金行权 12:借款标流标 13:借款标撤消 14:取消提现
	 * 15：直通车撤消 16:提现审核不通过 17：直通车流车】 begin
	 */
	public static final Integer NET_TYPE_BORROW_SUCCESS = 0;
	public static final Integer NET_TYPE_WEB_AWARD = 1;
	public static final Integer NET_TYPE_REPAYMENT_DEDUCT = 2;
	public static final Integer NET_TYPE_REPAYMENT_BACK = 3;
	public static final Integer NET_TYPE_WEBPAY_REPAYMENT_BACK = 4;
	public static final Integer NET_TYPE_AFTER_WEB_REPAY = 5;
	public static final Integer NET_TYPE_INVEST_BACK = 6;
	public static final Integer NET_TYPE_EARLY_REPAYMENT_DEDUCT = 7;
	public static final Integer NET_TYPE_EARLY_REPAYMENT_BACK = 8;
	public static final Integer NET_TYPE_LATER_INVEST_BACK = 9;
	public static final Integer NET_TYPE_FIRST_UNLOCK_SUCCESS = 10;
	public static final Integer NET_TYPE_STOCK_TO_MONEY = 11;
	public static final Integer NET_TYPE_BORROW_FLOW = 12;
	public static final Integer NET_TYPE_BORROW_CANCEL = 13;
	public static final Integer NET_TYPE_CASH_CANCEL = 14;
	public static final Integer NET_TYPE_FIRST_CANCEL = 15;
	public static final Integer NET_TYPE_CASH_FAILED = 19;
	public static final Integer NET_TYPE_CASH_INVALID = 20;
	/*****************************************************************************************
	 * 净值额度表 类型【0：借款入账，1：网站奖励,2:还款扣除，3：还款入帐 4：垫付入帐 5：垫付后还款扣除 6：垫付还款后非VIP收取利息
	 * 7:提前还款扣除 8：提前还款入帐 9：收取罚息:10:直通车解锁 11:现金行权 】 结束
	 */

	/** 新闻公告下-插件上传图片到linux下目录的路径 */
	// public static final String NET_LINUX_URL_PATH =
	// "/opt/tomcat7055-console/image.gcjr.com/ROOT/"; // 生产
	// public static final String NET_LINUX_URL_PATH =
	// "/opt/tomcat7055-console/image.gcjr.com/ROOT/"; // 生产
	public static final String NET_LINUX_URL_PATH = "/opt/tomcat-console/image.gcjr.com/ROOT/"; // 测试212

	/** VIP会员等级 0：普通VIP 1：终身顶级会员 begin */
	public static final Integer VIP_LEVEL_ONE = 0;
	public static final Integer VIP_LEVEL_TWO = 1;
	/** VIP会员等级 0：普通VIP 1：终身顶级会员 end */

	/** VIP会员等级状态 0：正常 -1：失效 begin */
	public static final Integer VIP_LEVEL_STATUS_ENABLE = 0;
	public static final Integer VIP_LEVEL_STATUS_DISABLE = -1;
	/** VIP会员等级状态 0：正常 -1：失效 end */

	/**
	 * 黑名单禁止操作类型【1：禁止手动投标，2：禁止认购直通车，3：禁止认购债权转让，4：禁止设置自动投标，5：禁止线上充值，6：禁止提现，7：
	 * 禁止发净值标, 8：直通车下车，9：禁止微信消息群推，10：禁止微信消息群推】 END
	 */
	public static final Integer BLACK_TYPE_TENDER_BORROW = 1;
	public static final Integer BLACK_TYPE_TENDER_FIRST = 2;
	public static final Integer BLACK_TYPE_TENDER_TRANSFER = 3;
	public static final Integer BLACK_TYPE_SET_AUTO_INVEST_CONFIG = 4;
	public static final Integer BLACK_TYPE_ONLINE_RECHARGE = 5;
	public static final Integer BLACK_TYPE_CASH = 6;
	public static final Integer BLACK_TYPE_PUBLIC_BORROW = 7;
	public static final Integer BLACK_TYPE_FIRST_DEBUS = 8;
	public static final Integer BLACK_TYPE_WX_SEND_MESSAGE = 9;
	/**
	 * 黑名单禁止操作类型【1：禁止手动投标，2：禁止认购直通车，3：禁止认购债权转让，4：禁止设置自动投标，5：禁止线上充值，6：禁止提现，7：
	 * 禁止发净值标, 8：直通车下车，9：禁止微信消息群推，10：禁止微信消息群推】 END
	 */

	/*********************** 连连支付相关信息 开始 ***************************************/
	// 发送方标识正式帐号：201411031000082502 ,测试帐号：201408071000001546 201408071000001540
	// 201408071000001543 201408071000001541
	public static String ONLINE_PAY_LIANLIANPAY_OID_PARTNER = new String("201411031000082502");
	public static String ONLINE_PAY_LIANLIANPAY_SIGNTYPE = "RSA"; // 签名类型RSA或者MD5
	// 私钥路径 lianlianpay/private_key.txt测试：lianlianpay/test_private_key.key
	public static String ONLINE_PAY_LIANLIANPAY_RPIVATE_KEY = new String("lianlianpay/private_key.txt");
	// 公钥
	public static String ONLINE_PAY_LIANLIANPAY_PUB_KEY = new String(
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB");
	public static String ONLINE_PAY_LLWAP_CARD_UNBIND_URL = new String("https://yintong.com.cn/traderapi/bankcardunbind.htm");// 银行卡解约API接口
	/************************ 连连支付手机支付相关信息 结束 ***********************************/

	/** 算新注册用户的开始时间　 */
	public static String NEW_REGISTER_USER_TIME = "2015-04-21 18:00:00";

	/** 抽奖机会来源类型 begin */
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_RECOMMEND = "1"; // 推荐奖励
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_INVEST_AWARD = "2"; // 投资擂台奖
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_FIRST_BORROW_INVEST = "3"; // 直通车投资奖
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_FORUM_SIGN = "4"; // 论坛签到奖励
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_NEW_REGISTER = "5"; // 新手注册奖
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_REAL_NAME_APPRO = "6"; // 实名认证奖
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_FIRST_INVEST = "7"; // 首次投资奖
	public static String LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_INVEST_GRADE = "8"; // 新用户理财里程碑奖
	/** 抽奖机会来源类型 end */
	/************************ 借款标审核状态 ***************************************************/
	public static Integer BORROW_APPSTATUS_DRAFT_CODE = -1;
	public static Integer BORROW_APPSTATUS_WAIT_CODE = 0;
	public static Integer BORROW_APPSTATUS_FIRST_PASS_CODE = 1;
	public static Integer BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE = 2;
	public static Integer BORROW_APPSTATUS_FINAL_PASS_CODE = 3;
	public static Integer BORROW_APPSTATUS_RECHECK_PASS_CODE = 4;
	/************************ 借款标审核状态 ***************************************************/
	/************************ 借款标状态 ***************************************************/
	public static Integer BORROW_STATUS_DRAFT_CODE = 0;
	public static Integer BORROW_STATUS_NEW_CODE = 1;
	public static Integer BORROW_STATUS_TEND_CODE = 2;
	public static Integer BORROW_STATUS_SUCCESS_CODE = 3;
	public static Integer BORROW_STATUS_REPAY_CODE = 4;
	public static Integer BORROW_STATUS_END_CODE = 5;
	public static Integer BORROW_STATUS_FAILING_CODE = -1;
	public static Integer BORROW_STATUS_REVOCATION_CODE = -2;
	public static Integer BORROW_STATUS_NOPASS_CODE = -3;
	public static Integer BORROW_STATUS_WEBPAY_CODE = 42;
	/************************ 借款标状态 ***************************************************/
	/************************ 是否审核通过 ***************************************************/
	public static Integer APPROVE_BORROW_PASS = 0;
	public static Integer APPROVE_BORROW_NO_PASS = 1;
	/************************ 是否审核通过 ***************************************************/
	/************************ 借款标种类 1：信用标，2：抵押标，3：净值标，4：秒标 ，5：担保） 开始 ***********************/
	public static Integer BORROW_TYPE_RECOMMEND = 1;
	public static Integer BORROW_TYPE_PLEDGE = 2;
	public static Integer BORROW_TYPE_NETVALUE = 3;
	public static Integer BORROW_TYPE_SEC = 4;
	public static Integer BORROW_TYPE_GUARANTEED = 5;
	/************************ 借款标种类 1：信用标，2：抵押标，3：净值标，4：秒标 ）结束 **********************/
	/***************** 待还记录 网站代还状态 0--未代还 1--已代还 begin ******************************/
	public Integer REPAYMENTRECORD_WEBSTATUS_NO_PAY = 0;
	public Integer REPAYMENTRECORD_WEBSTATUS_HAVE_PAY = 1;
	/***************** 待还记录 网站代还状态 0--未代还 1--已代还 end ******************************/

	/***************** 待还记录 状态 0---未还 1---已还 begin ******************************/
	public Integer REPAYMENTRECORD_STATUS_NO_PAY = 0;
	public Integer REPAYMENTRECORD_STATUS_HAVE_PAY = 1;
	/***************** 待还记录 状态 0--未代还 1--已代还 end ******************************/
	/************************ 借款标 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息 ,4:按天到期还款】 开始 **/
	public Integer BORROW_STYLE_NO_LIMIT = 0;
	public Integer BORROW_STYLE_MONTH_INSTALMENTS = 1;
	public Integer BORROW_STYLE_MONTH_PAY_INTEREST = 2;
	public Integer BORROW_STYLE_DUE_PAY_ALL = 3;
	public Integer BORROW_STYLE_DAY_DUE_PAY = 4;
	/************************ 借款标 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息,4:按天到期还款】 结束 **/
	/****************** 待收标的状态,-2:作废，-1:已债权转让，0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 begin *************/
	public static Integer COLLECTION_RECORD_STATUS_FAILER = -2;
	public static Integer COLLECTION_RECORD_STATUS_TRANSFERED = -1;
	public static Integer COLLECTION_RECORD_STATUS_UNPAY = 0;
	public static Integer COLLECTION_RECORD_STATUS_HAVE_PAY = 1;
	public static Integer COLLECTION_RECORD_STATUS_WEBPAY = 2;
	public static Integer COLLECTION_RECORD_STATUS_AFTERWEBPAY = 3;
	/**
	 * 权证人员投标金额
	 */
	public static BigDecimal BUSINESS_TENDER_MONEY = new BigDecimal(50);
	/****************** 待收标的状态,-2:作废，-1:已债权转让，0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 end *************/
	/************************ 逾期利率 *****************************************/
	public static String OUT_OF_DAYE_RATE = "0.002";
	/************************ 逾期利率 *****************************************/
	/** 直通车开通明细记录 开通类型 1：首次开通，2：上车 */
	public static Integer FIRST_BORROW_TENDER_TYPE_ONE = 1;
	public static Integer FIRST_BORROW_TENDER_TYPE_TWO = 2;
	
	/********************* 图片上传文件大小 (单位：M) ,比如说身份证 ********************/
	public Long IMAGE_MAX_SIZE = 1l;

	/********************* 图片上传文件支持的类型 ，比如说身份证 ********************/
	public String[] IMAGE_FILE_TYPE_LIST = new String[] { ".jpg", ".jpeg", ".gif", ".png" };
	
	/** 活期宝转出类型 begin */
	public static Integer CUR_OUT_TYPE_TO_USE_MONEY = 0; // 活期宝转出到可用余额
	public static Integer CUR_OUT_TYPE_TO_TENDER_BORROW = 1; // 活期宝转出投标
	public static Integer CUR_OUT_TYPE_TO_TENDER_FIRST_BORROW = 2; // 活期宝转出开通直通车
	public static Integer CUR_OUT_TYPE_TO_BUY_TRANSFER = 3; // 活期宝转出购买债权转让
	public static Integer CUR_OUT_TYPE_TO_BUY_FIRST_BORROW_TRANSFER = 4; // 活期宝转出购买直通车转让
	public static Integer CUR_OUT_TYPE_TO_BUY_REGULAR_BAO = 5; // 活期宝转出购买定期宝
	/** 活期宝转出类型 end */

	/** 活期宝转出资金明细操作类型 begin */
	public static Integer CUR_OUT_TYPE_TO_USE_MONEY_CAPITAL_DETAIL = 201; // 活期宝转出到可用余额
	public static Integer CUR_OUT_TYPE_TO_TENDER_BORROW_CAPITAL_DETAIL = 202; // 活期宝转出投标
	public static Integer CUR_OUT_TYPE_TO_TENDER_FIRST_BORROW_CAPITAL_DETAIL = 203; // 活期宝转出开通直通车
	public static Integer CUR_OUT_TYPE_TO_BUY_TRANSFER_CAPITAL_DETAIL = 204; // 活期宝转出购买债权转让
	public static Integer CUR_OUT_TYPE_TO_BUY_FIRST_BORROW_TRANSFER_CAPITAL_DETAIL = 205; // 活期宝转出购买直通车转让
	public static Integer CUR_OUT_TYPE_TO_BUY_REGULAR_BAO_CAPITAL_DETAIL = 206; // 活期宝转出购买定期宝
	/** 活期宝转出资金明细操作类型 end */

	/** 普通账户 活期宝转入资金明细操作类型 begin */
	public static Integer CUR_OUT_TYPE_TO_USE_MONEY_COMMON_CAPITAL_DETAIL = 101; // 活期宝转入可用余额
	public static Integer CUR_OUT_TYPE_TO_TENDER_BORROW_COMMON_CAPITAL_DETAIL = 102; // 活期宝投标转入
	public static Integer CUR_OUT_TYPE_TO_TENDER_FIRST_BORROW_COMMON_CAPITAL_DETAIL = 103; // 活期宝开通直通车转入
	public static Integer CUR_OUT_TYPE_TO_BUY_TRANSFER_COMMON_CAPITAL_DETAIL = 104; // 活期宝购买债权转入
	public static Integer CUR_OUT_TYPE_TO_BUY_FIRST_BORROW_TRANSFER_COMMON_CAPITAL_DETAIL = 105; // 活期宝购买直通车转让转入
	public static Integer CUR_OUT_TYPE_TO_BUY_REGULAR_BAO_COMMON_CAPITAL_DETAIL = 106; // 活期宝购买定期宝转入
	/** 普通账户 活期宝转入资金明细操作类型 end */
	/************************ 短信发送状态设置 开始 *****************************************/
	public static int SMS_SEND_STATUS_WAIT = 0;// 等待发送
	public static int SMS_TEMPLATE_TYPE_MODIFY_MOBILE_REQUEST = 6;// 修改手机号


	/************************ 存管还款类型 开始 *****************************************/
	public static int CUSTODY_REPAY_TYPE_NORMAL = 1;// 正常还款
	public static int CUSTODY_REPAY_TYPE_EARLY= 2;// 提前还款


	public static int CUSTODY_RECON_REPAY = 2;// 对账本息还款类型

	

}
