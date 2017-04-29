/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CGBusinessConstants.java
 * @package com.cxdai.console.common.statics 
 * @author tanghaitao
 * @version 0.1 2016年5月18日
 */
package com.cxdai.console.common.statics;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CGBusinessConstants.java
 * @package com.cxdai.console.common.statics 
 * @author tanghaitao
 * @version 0.1 2016年5月18日
 */

public interface CGBusinessConstants {

	//项目ID必须以我行分配给平台的instid作为前缀
	public static String BORROW_PIReq_ID="GCJR";
	
	//e_messges_record 关联号
	public static String  RELATENO="MGL";
	
	//报文messgesID
	public static String  MSGID="MSG";
	
	//项目基本信息登记服务<PIReq>
	public static String PIREQ="PIReq";

	//单笔交易流水查询
	public static String  TSQID="TSQ";
	
	//复审通过，扣除账户冻结金额
	public static String TS="cg_tender_success";
	//复审通过，待收金额增加
	public static String CD="cg_collection_added";
	//复审通过，借款人账户资金增加
	public static String BS="cg_borrow_success";
	//存管复审通过，增加投资失败的金额
	public static String TF="cg_tender_failure";
	//撤存管标
	public static String CTS="cg_tender_failed";
	
	//项目投资信息登记请求
	public static String PTRReq="PTRReq";
		
	//项目投资信息登记响应
	public static String PTRRes="PTRRes";
	
	public static String UFBReq="UFBReq";
	
	public static String UFBRes="UFBRes";
	
	//余额查询AQReq
	public static String AQReq="AQReq";
		
	//余额查询响应AQRes
	public static String AQRes="AQRes";


		
	//商卡资金同步
	public static String SK_SYN="sk_syn";
	
	//平台投资冻结流水号
	public static String P2P_SERIAL_DJ="TDJ";
	
	//调用资金冻结接口
	public static String FBReq="FBReq";
	
	public static String FBRes="FBRes";
	
	//投标冻结类型
	public static String CG_TB="cg_tender_cold";
	
	
}
