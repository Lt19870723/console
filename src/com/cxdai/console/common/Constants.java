package com.cxdai.console.common;

import java.math.BigDecimal;

/**
 * <p>
 * Description:基本常量类<br />
 * </p>
 * 
 * @title Constants.java
 * @package com.cxdai.statics
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */

public interface Constants {

	public Integer CONSOLE_PAGE_SIZE = new Integer(20);
	public Integer CONSOLE_PAGE_SIZE2 = new Integer(10);
	public Integer CONSOLE_PAGE_SIZE3 = new Integer(5);
	public Integer CONSOLE_PAGE_SIZE4 = new Integer(100);
	/** 账户状态（-1：账户注销；0：正常状态） begin */
	public Integer MEMBER_STATUS_NORMAL = 0;
	public Integer MEMBER_STATUS_CANCELLED = -1;
	/** 账户状态（-1：账户注销；0：正常状态） end */

	/** 会员来源（0：网站注册 1：网贷第三方 2：易瑞通） begin */
	public Integer MEMBER_SOURCE_CHENGXINDAI = 0;
	public Integer MEMBER_SOURCE_WANGDAI3 = 1;
	public Integer MEMBER_SOURCE_ERIGHTOFFER = 2;
	/** 会员来源（0：网站注册 1：网贷第三方 2：易瑞通） end */

	/** 理财/借款用户（1 理财用户 0 借款用户） begin */
	public Integer MEMBER_FINANCIAL_USER = 1;
	public Integer MEMBER_BORROW_USER = 0;
	/** 理财/借款用户（1 理财用户 0 借款用户） end */

	/** 1 是 0 否', */
	public Integer YES = 1;
	public Integer NO = 0;
	/** 1 是 0 否', */

	/*
	 * 积分的类型(0登陆 1发帖 2 回复 3领工资/签到 4精华帖 5收藏 6顶贴 7邀请
	 * 8加好友,9考察报告,10首充1000积分奖励,11网站奖励) begin
	 */
	public int POINTS_TYPE_LOGIN = 0;
	public int POINTS_TYPE_POSTED = 1;
	public int POINTS_TYPE_REPLY = 2;
	public int POINTS_TYPE_SIGN = 3;
	public int POINTS_TYPE_ESSENCE = 4;
	public int POINTS_TYPE_COLLECT = 5;
	public int POINTS_TYPE_TOPSTICK = 6;
	public int POINTS_TYPE_INVITE = 7;
	public int POINTS_TYPE_ADDFRIEND = 8;
	public int POINTS_TYPE_INVESTIGATION_REPORT = 9;
	public int POINTS_TYPE_FIRST_RECHARGE = 10;
	public int POINTS_TYPE_WEB_AWARD = 11;
	public int POINTS_TYPE_ESSENCE_REPLY = 12; // 精华帖回复
	public int POINTS_TYPE_ESSENCE_CLICK = 13; // 精华帖查看
	public int POINTS_TYPE_ESSENCE_DEDUCT = 14; // 精华帖积分扣除
	public int POINTS_TYPE_PUNISH = 15; // 惩罚扣分
	public int POINTS_TYPE_SIGN_FLOOR = 16; // 签到踩楼

	public int ESSENCE_POINTS = 30; // 精华帖积分
	public int ESSENCE_DELETE_POINTS = -100;// 精华帖扣除积分

	public int CONFIG_POINT_TYPE = 1001; // rocky_configuration 表配置文件中积分类型

	/*
	 * 积分的类型(0登陆 1发帖 2 回复 3领工资 4精华帖 5收藏 6顶贴 7邀请 8加好友,9考察报告,10首充1000积分奖励 11网站奖励)
	 * end
	 */

	/*** 在线人数记录 0表示前台1表示后台 begin */
	public String ONLINE_COUNTER_SYSTEM_PORTAL = "0";
	public String ONLINE_COUNTER_SYSTEM_CONSOLE = "1";
	public String ONLINE_COUNTER_SYSTEM_BBS = "2";
	/*** 在线人数记录 0表示前台1表示后台 begin */

	/************************ 系统站内信类型设置 开始 *****************************************/
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_REGISTER_SUCCESS = 1;// 注册成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_MODIFY_LOGINPASSWORD_REQUEST = 2;// 修改登陆密码
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_MODIFY_LOGINPASSWORD_SUCCESS = 3;// 修改登陆密码成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_INVEST_SUCCESS = 4;// 手动投标成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_AUTO_INVEST_SUCCESS = 5;// 自动投标成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_ONLINE_RECHARGE = 6;// 线上充值
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_ONLINE_RECHARGE_SUCCESS = 7;// 线上充值成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_ONLINE_RECHARGE_FAIL = 8;// 线上充值失败
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_OFFLINE_RECHARGE = 9;// 线下充值
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_OFFLINE_RECHARGE_SUCCESS = 10;// 线下充值成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_OFFLINE_RECHARGE_FAIL = 11;// 线下充值失败
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_OFFLINE_RECHARGE_AWARD = 12;// 线下充值奖励
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_MODIFY_PAYPASSWORD_REQUEST = 13;// 修改交易密码
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_MODIFY_PAYPASSWORD_SUCCESS = 14;// 修改交易密码成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_RESET_LOGINPASSWORD_REQUEST = 15;// 重置登陆密码成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_RESET_PAYPASSWORD_REQUEST = 16;// 重置交易密码成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_BORROW_SUCCESS = 17;// 借款标发布成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_WEB_RECHARGE = 18;// 网站充值
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_FIND_PASSWORD = 19;// 找回密码
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_WEB_AWARD = 20;// 网站奖励
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_CASH_PAYMENT_SUCCESS = 21;// 提现打款成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_FIRST_TENDER_SUCCESS = 22;// 优先投标成功
	public int SYSTEM_MESSAGE_TEMPLATE_TYPE_FIRST_SUBSCRIBE_SUCCESS = 23;// 优先计划开通成功
	/************************ 系统站内信类型设置 结束 *****************************************/

	/************************ 系统站内信设置 开始 *****************************************/
	public int SYSTEM_MESSAGE_NOT_READ = 0;// 未读
	public int SYSTEM_MESSAGE_READ = 1;// 已读

	/************************ 系统站内信设置 结束 *****************************************/

	/************************ 还款类型 ***************************************************/
	public static String STYLE_PAY_INTEREST_MONTH_CODE = "2";
	public static String STYLE_PAY_INTEREST_MONTH_NAME = "按月付息到期还本";
	public static String STYLE_PAY_INTEREST_OR_PRINCIPAL_MONTH_CODE = "3";
	public static String STYLE_PAY_INTEREST_OR_PRINCIPAL_MONTH_NAME = "到期还本付息";
	public static String STYLE_PAY_INTEREST_OR_PRINCIPAL_DAY_CODE = "4";
	public static String STYLE_PAY_INTEREST_OR_PRINCIPAL_DAY_NAME = "按天到期还款";
	public static String STYLE_PAY_INSTALMENT_MONTH_CODE = "1";
	public static String STYLE_PAY_INSTALMENT_MONTH_NAME = "按月分期还款";
	/************************ 还款类型 ***************************************************/

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
	/************************ 借款标审核状态 ***************************************************/
	public static Integer BORROW_APPSTATUS_DRAFT_CODE = -1;
	public static Integer BORROW_APPSTATUS_WAIT_CODE = 0;
	public static Integer BORROW_APPSTATUS_FIRST_PASS_CODE = 1;
	public static Integer BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE = 2;
	public static Integer BORROW_APPSTATUS_FINAL_PASS_CODE = 3;
	public static Integer BORROW_APPSTATUS_RECHECK_PASS_CODE = 4;
	/************************ 借款标审核状态 ***************************************************/
	/************************ 借款标审核类型 ***************************************************/
	public static Integer BORROW_CHECK_TYPE_FIRST = 0;
	public static Integer BORROW_CHECK_TYPE_ANTIFRAUD = 1;
	public static Integer BORROW_CHECK_TYPE_FINAL = 2;
	public static Integer BORROW_CHECK_TYPE_RECHECK = 3;
	/************************ 借款标审核类型 ***************************************************/

	/************************ 借贷产品类型：1-诚薪贷 2-诚商贷 3-净值贷 开始 ***********************/
	public static int BORROW_PRODUCT_TYPE_SALARIAT = 1;
	public static int BORROW_PRODUCT_TYPE_COMMERCE = 2;
	public static int BORROW_PRODUCT_TYPE_NETVALUE = 3;
	/************************ 借贷产品类型：1-诚薪贷 2-诚商贷 3-净值贷 结束 ***********************/

	/************************ 借款标种类 1：信用标，2：抵押标，3：净值标，4：秒标 ，5：担保） 开始 ***********************/
	public static Integer BORROW_TYPE_RECOMMEND = 1;
	public static Integer BORROW_TYPE_PLEDGE = 2;
	public static Integer BORROW_TYPE_NETVALUE = 3;
	public static Integer BORROW_TYPE_SEC = 4;
	public static Integer BORROW_TYPE_GUARANTEED = 5;
	/************************ 借款标种类 1：信用标，2：抵押标，3：净值标，4：秒标 ）结束 **********************/

	/************************ 借款标 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息 ,4:按天到期还款】 开始 **/
	public Integer BORROW_STYLE_NO_LIMIT = 0;
	public Integer BORROW_STYLE_MONTH_INSTALMENTS = 1;
	public Integer BORROW_STYLE_MONTH_PAY_INTEREST = 2;
	public Integer BORROW_STYLE_DUE_PAY_ALL = 3;
	public Integer BORROW_STYLE_DAY_DUE_PAY = 4;
	/************************ 借款标 还款方式【 0：没有限制， 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息,4:按天到期还款】 结束 **/

	/************************ 是否正在自动投标 ***************************************************/
	public static Integer BORROW_IS_AUTOTENDER_NO = 0;
	public static Integer BORROW_IS_AUTOTENDER_YES = 1;
	/************************ 是否正在自动投标 ***************************************************/
	/************************ 是否审核通过 ***************************************************/
	public static Integer APPROVE_BORROW_PASS = 0;
	public static Integer APPROVE_BORROW_NO_PASS = 1;
	/************************ 是否审核通过 ***************************************************/
	/************************ 自动审核 ***************************************************/
	public static Integer AUTO_CHECK_USERID = -1;
	public static String AUTO_CHECK_REMARK = "系统审核";
	/************************ 自动审核 ***************************************************/

	/***** BBS开始 *******/
	public int BBS_ITEMS_PAGE_SIZE = 20; // items default page size
	public int BBS_NOTES_PAGE_SIZE = 10; // notes default page size

	public int BBS_ROLE_CODE_VISITOR = 0; // 游客
	public int BBS_ROLE_CODE_USER = 1; // 普通用户
	public int BBS_ROLE_CODE_MODERATOR = 2; // 当前版版主
	public int BBS_ROLE_CODE_SUPERADMIN = 3; // 超级管理员
	public int BBS_ROLE_CODE_OWNER = 4; // 自己发的帖

	public int BBS_OPERATION_TYPE_CATEGORY = 0;// 加精
	public int BBS_OPERATION_TYPE_HOT = 1;// 加热门
	public int BBS_OPERATION_TYPE_DELETE = 2;// 删帖
	public int BBS_OPERATION_TYPE_SCREEN = 3;// 屏蔽
	public int BBS_OPERATION_TYPE_TOP = 4;// 置顶
	public int BBS_OPERATION_TYPE_COLLECT = 5;// 收藏
	public int BBS_OPERATION_TYPE_SUPPORT = 6;// 顶帖
	public int BBS_OPERATION_TYPE_MOVE = 7;// 移帖
	public int BBS_OPERATION_TYPE_NOTE_SCREEN = 8;// 回复屏蔽
	public int BBS_OPERATION_TYPE_NOTE_DELETE = 9;// 回复删除
	/***** BBS结束 *******/

	/** 是否通过实名认证 【-1：审核不通过，0：等待审核，1：审核通过】 begin */
	public Integer REALNAME_APPR_ISPASSED_REJECT = -1;
	public Integer REALNAME_APPR_ISPASSED_WAIT_APPROVE = 0;
	public Integer REALNAME_APPR_ISPASSED_PASSED = 1;
	/** 是否通过实名认证 【-1：审核不通过，0：等待审核，1：审核通过】 end */

	/** 是否通过VIP审核【0:初始状态；1：审核通过；-1：审核不通过】 begin */
	public Integer VIP_APPRO_PASSED_NO = -1;
	public Integer VIP_APPRO_PASSED_WAIT_APPROVE = 0;
	public Integer VIP_APPRO_PASSED_YES = 1;
	/** 是否通过VIP审核 【-1：审核不通过，0：等待审核，1：审核通过】 end */

	/** 字典项配置 配置类型从1开始排序【1：资金流动说明；2：邮箱关联；3：投资等级；4：信用等级；】 begin */
	public Integer CONFIGURATION_TYPE_MONEY_FLOW = 1;
	public Integer CONFIGURATION_TYPE_EMAIL = 2;
	public Integer CONFIGURATION_TYPE_INVEST_LEVEL = 3;
	public Integer CONFIGURATION_TYPE_CREDIT_LEVEL = 4;
	/** 字典项配置 配置类型从1开始排序【1：资金流动说明；2：邮箱关联；3：投资等级；4：信用等级；】 end */

	/** 短信模板 逻辑删除标识【0：有效；-1：无效】 * begin */
	public Integer SMS_TEMPLATE_FLAG_YES = 0;
	public Integer SMS_TEMPLATE_FLAG_NO = 1;
	/** 短信模板 逻辑删除标识【0：有效；-1：无效】 * end */

	/** 短信发送 供应商类型，0:港奥资迅，1：漫道 begin */
	public Integer SMS_RECORD_VENDOR_TYPE_ZUCP = 1;
	/** 短信发送 供应商类型，0:港奥资迅，1：漫道 end */

	/***************** 待还记录 网站代还状态 0--未代还 1--已代还 begin ******************************/
	public Integer REPAYMENTRECORD_WEBSTATUS_NO_PAY = 0;
	public Integer REPAYMENTRECORD_WEBSTATUS_HAVE_PAY = 1;
	public Integer REPAYMENTRECORD_WEBSTATUS_BANK_PAY = 5;
	/***************** 待还记录 网站代还状态 0--未代还 1--已代还 end ******************************/

	/***************** 待还记录 状态 0---未还 1---已还 begin ******************************/
	public Integer REPAYMENTRECORD_STATUS_NO_PAY = 0;
	public Integer REPAYMENTRECORD_STATUS_HAVE_PAY = 1;
	public Integer REPAYMENTRECORD_STATUS_BANK_PAY = 4;
	/***************** 待还记录 状态 0--未代还 1--已代还 end ******************************/

	/*********************** 充值种类设置 开始 ***************************************/
	public static int RECHARGE_TYPE_ONLINE = 1;// 在线充值
	public static int RECHARGE_TYPE_OFFLINE = 2;// 线下充值

	/************************ 充值种类设置 结束 *****************************************/

	/*********************** 充值状态设置 开始 ***************************************/
	public static int RECHARGE_STATUS_APPROVING = 0;// 充值审核中
	public static int RECHARGE_STATUS_SUCCESS = 1;// 充值成功
	public static int RECHARGE_STATUS_FAILURE = 9;// 充值失败
	public static int RECHARGE_STATUS_ONLINE_WAIT = 2;// 在线充值待付款
	public static int RECHARGE_STATUS_FIRST_APPROVE_SUCCESS = 3;// 充值初审成功

	/************************ 充值状态设置 结束 *****************************************/

	/************************ 第三方支付平台设置 开始 开始 *****************************************/
	public static int ONLINE_TYPE_CHINABANK = 1;// 网银在线
	public static String ONLINE_TYPE_CHINABANK_NAME = "网银在线";// 网银在线
	public static int ONLINE_TYPE_GOPAY = 2;// 国付宝
	public static String ONLINE_TYPE_GOPAY_NAME = "国付宝";// 国付宝
	public static int ONLINE_TYPE_SHENGPAY = 3;// 盛付通
	public static String ONLINE_TYPE_SHENGPAY_NAME = "盛付通";// 盛付通
	public static int ONLINE_TYPE_SINAPAY = 4;// 新浪支付
	public static String ONLINE_TYPE_SINAPAY_NAME = "新浪支付";// 新浪支付
	public static int ONLINE_TYPE_LIANLIANAPAY = 5;// 连连支付
	public static String ONLINE_TYPE_LIANLIANPAY_NAME = "连连支付";// 新浪支付
	/************************ 第三方支付平台设置 开始 结束 *****************************************/

	/** 是否理财用户 **/
	public static String IS_FINANCIAL_USER = "1";// 理财用户
	public static String IS_BORROWER_USER = "0";// 借款用户
	/** 是否理财用户 **/

	/************************ 逾期利率 *****************************************/
	public static String OUT_OF_DAYE_RATE = "0.002";
	/************************ 逾期利率 *****************************************/

	/********************** 提现记录状态 -1：审核失败；0：申请提现；1：审核通过；2：打款结束 3:取消提现 4:提现作废 5:打款失败】 开始 ********************/
	public Integer CASH_RECORD_STATUS_APPROVE_FAILURE = -1;
	public Integer CASH_RECORD_STATUS_APPLYING = 0;
	public Integer CASH_RECORD_STATUS_APPROVE_SUCCESS = 1;
	public Integer CASH_RECORD_STATUS_CASH_FINISH = 2;
	public Integer CASH_RECORD_STATUS_CANCEL_CASH = 3;
	public Integer CASH_RECORD_STATUS_INVALID_CASH = 4;
	public Integer CASH_RECORD_STATUS_FAILD_CASH = 5;
	/********************** 提现记录状态 -1：审核失败；0：申请提现；1：审核通过；2：打款结束 3:取消提现 4:提现作废 5:打款失败】 结束 ********************/

	/**
	 * 优选计划状态 begin 0:草稿标
	 * 1：新标,审核中，2：审核不通过，3：审核通过投标中，4：满标复审中，5：满标审核通过，6：满标审核拒绝，-1
	 * ：流标，-2：被撤销,-3:已过期,-4:已删除
	 */
	public static Integer FIRST_BORROW_STATUS_NEW = 0;
	public static Integer FIRST_BORROW_STATUS_TO_APPROVE = 1;
	public static Integer FIRST_BORROW_STATUS_APPROVE_REJECT = 2;
	public static Integer FIRST_BORROW_STATUS_APPROVE_PASS = 3;
	public static Integer FIRST_BORROW_STATUS_TO_SUCCESS_APPROVE = 4;
	public static Integer FIRST_BORROW_STATUS_SUCCESS_APPROVE_PASS = 5;
	public static Integer FIRST_BORROW_STATUS_SUCCESS_APPROVE_REJECT = 6;
	public static Integer FIRST_BORROW_STATUS_LOSE = -1;
	public static Integer FIRST_BORROW_STATUS_CANCEL = -2;
	public static Integer FIRST_BORROW_STATUS_EXPIRE = -3;
	public static Integer FIRST_BORROW_STATUS_DELETED = -4;
	/**************** 优选计划状态 end *************/

	/**
	 * 审核类型， begin 0 ：发布审核 1：满标审核
	 */
	public static Integer APPROVE_STYLE_NEW_APPROVE = 0;
	public static Integer APPROVE_STYLE_SUCCESS_APPROVE = 1;
	/**************** 审核类型 end *************/

	/**
	 * 审核状态， begin 0 ：审核拒绝 1 审核通过
	 */
	public static Integer APPROVE_PASS = 1;
	public static Integer APPROVE_REJECT = 0;
	/**************** 审核状态 end *************/

	/**
	 * 优先投标计划开通明细 投标状态 begin 0 ：投标中 1 ：投标成功 2：投标失败
	 */
	public static Integer TENDER_DETAIL_STATUS_DOING = 0;
	public static Integer TENDER_DETAIL_STATUS_SUCCESS = 1;
	public static Integer TENDER_DETAIL_STATUS_FAILURE = 2;
	/************** 优先投标计划开通明细 投标状态 end */

	/**
	 * 优先投标计划最终开通 状态 begin 状态 0 ：未失效 1 ：已失效 2:解锁中
	 */
	public static Integer TENDER_REAL_STATUS_AVAILABLE = 0;
	public static Integer TENDER_REAL_STATUS_INVALID = 1;
	public static Integer TENDER_REAL_STATUS_UNLOCKING = 2;
	/************** 优先投标计划开通明细 投标状态 end */

	/********************** 转可提记录状态 -1：审核失败；0：申请转可提；1：审核通过；-2:取消转可提；】 开始 ********************/
	public static Integer TO_DRAW_LOG_STATUS_APPROVE_FAILURE = -1;
	public static Integer TO_DRAW_LOG_STATUS_APPLYING = 0;
	public static Integer TO_DRAW_LOG_STATUS_APPROVE_SUCCESS = 1;
	public static Integer TO_DRAW_LOG_STATUS_CANCEL_CASH = -2;
	/********************** 转可提记录状态 -1：审核失败；0：申请转可提；1：审核通过；-2:取消转可提；】 结束 ********************/

	public static BigDecimal TO_DRAW_LOG_FEE_APR = new BigDecimal(0.005); // 转可提现手续费费率

	/******************************* 投标方式（0：手动投标，1：自动投标，2：优先投标） ***********************************/
	public static Integer TENDER_TYPE_MANUAL = 0;
	public static Integer TENDER_TYPE_AUTO = 1;
	public static Integer TENDER_TYPE_FIRST = 2;

	/******************** 计算器中借款类型 开始 ******************************/
	public static int CALCULATOR_CATEGORY_MONTH = 0;// 按月借款
	public static int CALCULATOR_CATEGORY_DAY = 1;// 按天借款
	/******************** 计算器中借款类型结束 ******************************/

	/******************** 计算器中还款方式 开始 ******************************/
	public static int CALCULATOR_STYLE_WXZ = 0; // 无限制
	public static int CALCULATOR_STYLE_DEBXHK = 1;// 等额本息还款
	public static int CALCULATOR_STYLE_AYFXDQHB = 2;// 按月付息到期还本
	public static int CALCULATOR_STYLE_DQHBFX = 3;// 到期还本付息
	/******************** 计算器中还款方式 结束 ******************************/

	/******************** 计算器中每期利息保留的小数位数 开始 ******************************/
	public static int CALCULATOR_INTEREST_NUM = 12; // 计算器中每期利息保留的小数位数
	/******************** 计算器中每期利息保留的小数位数 结束 ******************************/

	/******************** 系统中处理金额的小数位数 开始 ******************************/
	public static int CALCULATOR_MONEY_NUM = 2; // 系统中处理金额的小数位数
	/******************** 系统中处理金额的小数位数 结束 ******************************/

	/******************** 系统中处理金额的小数位数 开始 ******************************/
	public static int CALCULATOR_MONEY_NUM_FOR_INVEST = 16; // 系统中处理金额的小数位数
	/******************** 系统中处理金额的小数位数 结束 ******************************/

	/******************** 计算器中管理费收取百分比 开始 ******************************/
	public static double CALCULATOR_POUNDAGE_ONE_STEP = 3; // 计算器中管理费收取百分比(第一级收费百分比,按月付息到期还本和按月到期还本付息)
	public static double CALCULATOR_POUNDAGE_TWO_STEP = 4.5; // 计算器中管理费收取百分比(第二级收费百分比,按月付息到期还本和按月到期还本付息)
	public static double CALCULATOR_POUNDAGE_THREE_STEP = 6; // 计算器中管理费收取百分比(第三级收费百分比,按月付息到期还本和按月到期还本付息)
	public static double CALCULATOR_POUNDAGE_DAY = 0.1; // 计算器中管理费收取百分比(按天到期还本付息)
	public static double CALCULATOR_POUNDAGE_DEBXHK = 1; // 计算器中管理费收取百分比(按月等额本息还款)

	/******************** 计算器中管理费收取百分比 结束 ******************************/

	/************************ 管理费固定利率开始 *****************************************/
	public static String MANAGEMENT_COST_RECOMMEND_BID = "1"; // 推荐标 1%
	public static String MANAGEMENT_COST_WORTH_MONTH_BID = "0.3"; // 净值标0.3%月
	public static String MANAGEMENT_COST_WORTH_DAY_BID = "0.3"; // 净值标0.3%天
	public static String MANAGEMENT_COST_WORTH_DAY_BID_2 = "0.1"; // 净值标0.1%天

	public static String MANAGEMENT_COST_PLEDGE_BID_MIN = "3"; // 1~3个月 3%
	public static String MANAGEMENT_COST_PLEDGE_BID_MEDIUM = "4.5";// 4,5,6个月
																	// 4.5%
	public static String MANAGEMENT_COST_PLEDGE_BID_MAX = "6";// 6个月以上 6%

	public static String MANAGEMENT_COST_PLEDGE_BID_DAY = "0.1";// 按天抵押标 0.1%

	/************************ 管理费固定利率结束 *****************************************/

	/********** 外部接口类型 ***********/
	public static final String DAILY_VOLUME_DATA = "daily_volume_data"; // 每日成交量数据（网贷天眼）
	public static final String PLATFORM_LOAD_DATA = "platform_load_data"; // 平台贷款数据（网贷天眼）
	public static final String PLATFORM_NOW_BORROW_DATA = "platform_now_borrow_data"; // 平台正在投标中的标的数据接口（网贷之家）
	public static final String PLATFORM_GET_BORROW_BY_DATA = "platform_get_borrow_by_data"; // 平台某天内满标的标的数据接口（网贷之家）
	public static final String PLATFORM_GET_BORROW_BY_NOW = "platform_get_borrow_by_now"; // 平台今天内满标的标的数据接口（网贷之家）
	public static final String PLATFORM_GET_PAIED_LOAN_INFO = "platform_get_paied_loan_info"; // 平台某天还款的标的信息接口（网贷之家）
	public static final String CHECK_USER_INFO = "check_user_info"; // 验证用户是否是平台用户接口（网贷之家）
	public static final String HSW_BORROW_INFO = "hsw_borrow_info"; // 海树网获取借款接口
	public static final String HSW_TENDERRECORD_INFO = "hsw_tenderrecord_info"; // 海树网获取投标记录接口
	public static final String HSW_OVERDUEBORROWS_INFO = "hsw_overdueborrows_info"; // 海树网获取逾期借款接口

	/******************** 0:借款标ID, 1:直通车id, 2:待还记录id 开始 **************************************/
	public static final Integer ACCOUNT_LOG_ID_TYPE_BORROW = 0;
	public static final Integer ACCOUNT_LOG_ID_TYPE_FIRST = 1;
	public static final Integer ACCOUNT_LOG_ID_TYPE_REPAYMENT = 2;
	/******************** 0:借款标ID, 1:直通车id, 2:待还记录id 结束 **************************************/

	/******************** 发送邮件ID类型, 1：满标发邮件，2：注册实名认证，3：还款提醒 开始 **************************************/
	public static final Integer MAIL_SEND_TYPE_BORROW = 1;
	public static final Integer MAIL_SEND_TYPE_REALNAME = 2;
	public static final Integer MAIL_SEND_TYPE_REPAY = 3;
	/******************** 发送邮件ID类型, 1：满标发邮件，2：注册实名认证，3：还款提醒 结束 **************************************/

	/******************** 债权转让 几种审核状态 1： 等待审核 2：审核不通过 0：审核通过 开始 **************************************/
	/**
	 * 待审核
	 * 
	 * @fields TRANSFER_STATU_WAIT
	 */
	public static final Integer TRANSFER_STATU_WAIT = 1;
	/**
	 * 初审核不通过
	 * 
	 * @fields TRANSFER_STATU_NOPASS
	 */
	public static final Integer TRANSFER_FIRST_STATU_NOPASS = 2;
	/**
	 * 初审核通过
	 * 
	 * @fields TRANSFER_STATU_PASS
	 */
	public static final Integer TRANSFER_FIRST_STATU_PASS = 3;

	/**
	 * 复审核不通过
	 * 
	 * @fields TRANSFER_STATU_NOPASS
	 */
	public static final Integer TRANSFER_FINAL_STATU_NOPASS = 4;
	/**
	 * 复审核通过
	 * 
	 * @fields TRANSFER_STATU_PASS
	 */
	public static final Integer TRANSFER_FINAL_STATU_PASS = 5;

	/**
	 * 投标中
	 * 
	 * @fields TRANSFER_STATU_ING
	 */
	public static final Integer TRANSFER_STATU_ING = 2;

	/**
	 * 新标待审核
	 * 
	 * @fields TRANSFER_STATU_WAIT
	 */
	public static final Integer TRANSFER_STATU_NEW_WAIT = 1;

	/**
	 * 3：满标复审中
	 * 
	 * @fields TRANSFER_FULL_RECHECK
	 */
	public static final Integer TRANSFER_FULL_RECHECK = 3;

	/**
	 * 4：转让完成，
	 * 
	 * @fields TRANSFER_SUCCESS
	 */
	public static final Integer TRANSFER_SUCCESS = 4;

	/**
	 * 流标
	 * 
	 * @fields TRANSFER_FLOW
	 */
	public static final Integer TRANSFER_FLOW = 5;

	/**
	 * 撤标
	 * 
	 * @fields TRANSFER_CANCEL
	 */
	public static final Integer TRANSFER_CANCEL = 6;

	/**
	 * 审核失败
	 */
	public static final Integer TRANSFER_STATU_CHECK_FAIL = 7;

	/******************** 债权转让 几种审核状态 1： 等待审核 2：审核不通过 0：审核通过 结束 **************************************/

	/******************** 提现操作日志 操作类型（3:取消提现,4:提现作废,5:打款失败) begin ****************************************************/
	public static final Integer CASHRECORD_LOG_TYPE_CANCEL = 3;
	public static final Integer CASHRECORD_LOG_TYPE_INVALID = 4;
	public static final Integer CASHRECORD_LOG_TYPE_FAILED = 5;
	/******************** 提现操作日志 操作类型（3:取消提现,4:提现作废,5:打款失败) end ****************************************************/
	

	/************** 银行卡 验证状态【0：未验证 1：验证通过】 ********************** begin */
	public static final Integer BANK_INFO_VERIFY_STATUS_NO = 0;
	public static final Integer BANK_INFO_VERIFY_STATUS_YES = 1;
	/************** 银行卡 验证状态【0：未验证 1：验证通过】 ********************** end */
	/****************** 待收标的状态,-2:作废，-1:已债权转让，0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 begin *************/
	public static Integer COLLECTION_RECORD_STATUS_FAILER = -2;
	public static Integer COLLECTION_RECORD_STATUS_TRANSFERED = -1;
	public static Integer COLLECTION_RECORD_STATUS_UNPAY = 0;
	public static Integer COLLECTION_RECORD_STATUS_HAVE_PAY = 1;
	public static Integer COLLECTION_RECORD_STATUS_WEBPAY = 2;
	public static Integer COLLECTION_RECORD_STATUS_AFTERWEBPAY = 3;
	public static Integer COLLECTION_RECORD_STATUS_BANK_WEBPAY = 5;
	/****************** 待收标的状态,-2:作废，-1:已债权转让，0:尚未还款，1：已还款，2：网站垫付 ，3:垫付后还款 end *************/
	/**
	 * 定期宝状态 begin 0:草稿标
	 * 1：新标,审核中，2：审核不通过，3：审核通过投标中，4：满标复审中，5：满标审核通过，6：满标审核拒绝，-1
	 * ：流标，-2：被撤销,-3:已过期,-4:已删除
	 */
	public static Integer FIX_BORROW_STATUS_NEW = 0;
	public static Integer FIX_BORROW_STATUS_TO_APPROVE = 1;
	public static Integer FIX_BORROW_STATUS_APPROVE_REJECT = 2;
	public static Integer FIX_BORROW_STATUS_APPROVE_PASS = 3;
	public static Integer FIX_BORROW_STATUS_TO_SUCCESS_APPROVE = 4;
	public static Integer FIX_BORROW_STATUS_SUCCESS_APPROVE_PASS = 5;
	public static Integer FIX_BORROW_STATUS_SUCCESS_APPROVE_REJECT = 6;
	public static Integer FIX_BORROW_STATUS_LOSE = -1;
	public static Integer FIX_BORROW_STATUS_CANCEL = -2;
	public static Integer FIX_BORROW_STATUS_EXPIRE = -3;
	public static Integer FIX_BORROW_STATUS_DELETED = -4;
	/**************** 定期宝状态 end *************/
	
	/******************** 银行本息还款数据上报类型  开始 **************************************/
	public static final Integer REPAYMENT_TYPE_NORMAL = 1;//正常
	public static final Integer REPAYMENT_TYPE_EARLY= 2;//提前
	public static final Integer REPAYMENT_TYPE_WEBPAY=3;//网站垫付
	public static final Integer REPAYMENT_TYPE_SETTLE=6;//提前结清
	/******************** 银行本息还款数据上报类型  结束 **************************************/
	
	
	
	/******************** 银行报文版本号  开始 **************************************/
	public static final String CUSTODY_MSG_VERSION ="1.0.0";
	/******************** 银行报文版本号  结束 **************************************/
	
	/******************** 银行报文流水号  开始 **************************************/
	public static final String CUSTODY_SERIAL_FRR1 ="FR1"; //本息还款-正常
	public static final String CUSTODY_SERIAL_FRR2 ="FR2"; //本息还款-提前
	public static final String CUSTODY_SERIAL_FRR3 ="FR3"; //本息还款-垫付
	/******************** 银行报文流水号  结束 **************************************/
}
