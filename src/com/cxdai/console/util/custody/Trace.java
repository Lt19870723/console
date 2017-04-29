//
// Copyright (C) 2003 ECC CORPORATION
//
// ALL RIGHTS RESERVED BY ECC CORPORATION, THIS PROGRAM
// MUST BE USED SOLELY FOR THE PURPOSE FOR WHICH IT WAS
// FURNISHED BY ECC CORPORATION, NO PART OF THIS PROGRAM
// MAY BE REPRODUCED OR DISCLOSED TO OTHERS, IN ANY FORM
// WITHOUT THE PRIOR WRITTEN PERMISSION OF ECC CORPORATION.
// USE OF COPYRIGHT NOTICE DOES NOT EVIDENCE PUBLICATION
// OF THE PROGRAM
//
//            ECC CONFIDENTIAL AND PROPRIETARY
//
////////////////////////////////////////////////////////////////////////////
//
// $Log: Trace.java,v $
// Revision 1.1  2008/05/23 04:36:18  wjm
// *** empty log message ***
//
// Revision 1.1  2008/04/01 00:31:55  wjm
// *** empty log message ***
//
// Revision 1.1  2007/12/21 07:51:37  wangqs
// *** empty log message ***
//
// Revision 1.1  2007/12/19 13:54:21  wangqs
// *** empty log message ***
//
// Revision 1.4  2005/09/18 18:33:02  chenhua
// Formater update.
//
// Revision 1.3  2005/09/17 12:42:03  xugl
// *** empty log message ***
//
// Revision 1.2  2005/09/08 06:10:01  chenhua
// ʹ�� log4j ��Ϊ Trace ��ʽ��
//
// Revision 1.1  2004/09/23 07:46:46  xugl
// add by xgl V40
//
// Revision 1.3  2004/09/14 09:10:17  xugl
// no message
//
// Revision 1.2  2004/09/14 02:48:16  xugl
// CHAGNE ECC TO ECC
//
// Revision 1.1.1.1  2004/06/28 14:42:06  xugl
// ��ʼ�汾���ύ�ð汾��Ŀ���ǣ�����CTP 1.0�汾
// submit by �����
//
// Revision 1.1.1.1  2004/04/10 10:52:52  xugl
// no message
//
//
package com.cxdai.console.util.custody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * 日志输出类，用于控制平台的所有日志输出。
 * 平台内唯一的日志输出接口。
 * 可用于向控制台输出信息，也可向文件输出信息。
 */
public class Trace {

	/**
	 * The Display type.
	 *
	 * @deprecated 使用 Information
	 */
	public static final String Display = "D";

	/**
	 * The Information type.
	 */
	public static final String Information = "I";

	/**
	 * The Warning type.
	 */
	public static final String Warning = "W";

	/**
	 * The Error type.
	 */
	public static final String Error = "E";

	/**
	 * The Severe type.
	 */
	public static final String Severe = "S";

	/**
	 * The Debug type.
	 */
	public static final String Debug = "T";

	/**
	 * The constants that represents all types.
	 */
	public static final String AllTypes = "D,I,W,E,S,T";

	/**
	 * Type level High.
	 */
	public static final String High = "H";

	/**
	 * Type level  Medium.
	 */
	public static final String Medium = "M";

	/**
	 * Type level  Low.
	 */
	public static final String Low = "L";

	/**
	 * The constants that represents all levels.
	 */
	public static final String AllLevels = "H,M,L";

	/**
	 * 用于控制日志信息是否向控制台输出.
	 */
	public static boolean enableTraces = true;

	/**
	 * The flag to indicate if the show originator option is activated. *
	 */
	public static boolean showStack;

	/**
	 * The complete name of trace method. *
	 */
	private static String traceMethodPath = null;


	/**
	 * message output to CTP monitor *
	 */
	public static boolean traceToMonitor = false;


	public static final String COMPONENT_UNASSIGNED = "->unAssigned";

	/**
	 * This constructor creates a Trace object.
	 */
	public Trace() {
	}


	/**
	 * Returns true if the specified level and the specified type are enabled
	 * for the specified component. If the specified component is not found then
	 * the UnAssigned component is used.
	 *
	 * @param component java.lang.String - The name of the component
	 * @param level     int - The level of trace
	 * @param type      int - The type of trace
	 *
	 * @return boolean
	 */
	private final static boolean doTrace(String component, String level,
	                                     String type) {
		if (!enableTraces) {
			return false;
		} else {
			if (component == null || component.trim().length() == 0) {
				component = COMPONENT_UNASSIGNED;
			}
			Logger log = Logger.getLogger(component);
			Priority pri = getPriority(type);
			return (log.isEnabledFor(pri));
		}
	}


	private static Level getPriority(String type) {

		if (type.equals(Trace.AllTypes)) return Level.ALL;
		if (type.equals(Trace.Debug)) return Level.DEBUG;
		//if(s.equals("FINE")) return Level.FINE;
		if (type.equals(Trace.Information)) return Level.INFO;
		if (type.equals(Trace.Warning)) return Level.WARN;
		if (type.equals(Trace.Error)) return Level.ERROR;
		if (type.equals(Trace.Severe)) return Level.FATAL;
		if (type.equals("OFF")) return Level.OFF;
		return Level.DEBUG;
	}


	/**
	 * Returns true if the showOriginator setting is enabled.
	 *
	 * @return boolean
	 */
	public static boolean getOriginator() {
		return showStack;
	}


	/**
	 * Resets and initializes trace facility with the original configuration.
	 */
	public static final synchronized void reset(String filePath) {

		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		DOMConfigurator.configure(filePath);
	}





	/**
	 * 跟踪异常信息处理
	 *
	 * @param anException java.lang.Exception
	 */
	public static final synchronized void trace(Exception anException) {


		trace(true, COMPONENT_UNASSIGNED, Trace.AllLevels, Trace.AllTypes, "????",
				anException);

	}

	/**
	 * 跟踪信息处理
	 *
	 * @param str java.lang.String
	 */
	public static final synchronized void trace(String str) {
		trace(true, COMPONENT_UNASSIGNED, Trace.AllLevels, Trace.AllTypes, "????", str);

	}

	/**
	 * 跟踪信息处理
	 *
	 * @param component   java.lang.String - 组件名称，在dse.ini的trace中定义，如：->CORE
	 * @param level       String - 跟踪信息级别：H, M,L,HML
	 * @param type        String - 跟踪信息类型：D,I,W,E,S,T,DIWEST
	 * @param TID         java.lang.String - 终端标识
	 * @param anException java.lang.Exception - 需要跟踪的异常信息
	 */
	public final static synchronized void trace(String component, String level,
	                                            String type, String TID, Exception anException) {
		ByteArrayOutputStream stack = new ByteArrayOutputStream();
		String stackString = new String();

		anException.printStackTrace(new java.io.PrintWriter(stack, true));
		stackString = stack.toString();

		stackString = stackString.replace((char) 13, '-');
		stackString = stackString.replace((char) 10, '-');
		stackString = stackString.replace((char) 9, '-');

		trace(true, component, level, type, TID, stackString);
	}

	private final static synchronized void outputMsg(String component,
	                                                 String level, String type, String TID, String str) {
		if (enableTraces) {


			if (str == null) {
				str = "null";

			}

			if (TID == null) {
				TID = "TID=????";

			}


			String stackString = new String();

			if (Trace.showStack) {
				ByteArrayOutputStream stack = new ByteArrayOutputStream();
				new Exception("Stack trace").printStackTrace(new PrintWriter(
						stack, true));
				try {
					stackString = stack.toString();
					int index = stackString.lastIndexOf(traceMethodPath);
					if (index != -1) {
						index = stackString.indexOf('\n', index);
						index += 2;
						stackString = stackString.substring(index, stackString
								.indexOf('\n', index) - 1);
					} else {
						stackString = new String("Unable to get originator ");
					}
				} catch (Exception e) {
					stackString = "Unable to get originator " + e.toString();
				}
				stackString = stackString + " ==> \n";
			}


			if (component == null || component.trim().length() == 0) {
				component = COMPONENT_UNASSIGNED;
			}
			Logger log = Logger.getLogger(component);
			log.log(getPriority(type), level + " - " + TID + " - " + stackString + str);
		}
		
	}

	/**
	 * 跟踪信息处理
	 *
	 * @param component java.lang.String - 组件名称，在dse.ini的trace中定义，如：->CORE
	 * @param level     String - 跟踪信息级别：H, M,L,HML
	 * @param type      String - 跟踪信息类型：D,I,W,E,S,T,DIWEST
	 * @param TID       java.lang.String - 终端标识
	 * @param str       java.lang.String - 跟踪信息
	 *
	 * @deprecated Removed!
	 */
	public final static void trace(String component, String level,
	                               String type, String TID, String str) {

		trace(true, component, level, type, TID, str);

	}

	/**
	 * 跟踪信息处理
	 *
	 * @param check       boolean 是否屏蔽没有加入跟踪列表的信息
	 * @param component   java.lang.String - 组件名称，在dse.ini的trace中定义，如：->CORE
	 * @param level       String - 跟踪信息级别：H, M,L,HML
	 * @param type        String - 跟踪信息类型：D,I,W,E,S,T,DIWEST
	 * @param TID         java.lang.String - 终端标识
	 * @param anException java.lang.Exception - 需要跟踪的异常信息
	 */
	public final static synchronized void trace(boolean check,
	                                            String component, String level, String type, String TID,
	                                            Exception anException) {
		ByteArrayOutputStream stack = new ByteArrayOutputStream();
		String stackString = new String();

		anException.printStackTrace(new java.io.PrintWriter(stack, true));
		stackString = stack.toString();

		stackString = stackString.replace((char) 13, '-');
		stackString = stackString.replace((char) 10, '-');
		stackString = stackString.replace((char) 9, '-');

		trace(check, component, level, type, TID, stackString);
	}

	/**
	 * 跟踪信息处理
	 *
	 * @param check     boolean 是否屏蔽没有加入跟踪列表的信息
	 * @param component java.lang.String - 组件名称，在dse.ini的trace中定义，如：->CORE
	 * @param level     String - 跟踪信息级别：H, M,L,HML
	 * @param type      String - 跟踪信息类型：D,I,W,E,S,T,DIWEST
	 * @param TID       java.lang.String - 终端标识
	 * @param message   java.lang.String - 跟踪信息
	 */
	public final static synchronized void trace(boolean check,
	                                            String component, String level, String type, String TID,
	                                            String message) {
		if (check) {
			if (doTrace(component, level, type)) {
				outputMsg(component, level, type, TID, message);
			}
		} else {
			outputMsg(component, level, type, TID, message);
		}
	}

	private static String appCategory = "->app";

	public final static void info(Object obj, String msg) {
		info(obj.getClass().getName() + " " + msg);
	}

	public final static void msg(Object obj, String msg) {
		System.out.println((obj.getClass().getName() + " " + msg));
	}

	public final static void debug(Object obj, String msg) {
		debug(obj.getClass().getName() + " " + msg);
	}


	public final static void debug(String msg) {
		trace(true, appCategory, Medium, Debug, null, msg);
	}

	public final static void warning(Object obj, String msg) {
		warning(obj.getClass().getName() + " " + msg);

	}

	public final static void warning(String msg) {
		trace(true, appCategory, Medium, Warning, null, msg);
	}

	public final static void error(Object obj, String msg) {
		error(obj.getClass().getName() + " " + msg);

	}

	public final static void error(String msg) {
		trace(true, appCategory, High, Error, null, msg);
	}

	public final static void severe(Object obj, String msg) {
		severe(obj.getClass().getName() + " " + msg);
	}

	public final static void severe(String msg) {
		trace(true, appCategory, High, Severe, null, msg);
	}

	public final static void info(String msg) {
		trace(true, appCategory, Medium, Information, null, msg);

	}

}
