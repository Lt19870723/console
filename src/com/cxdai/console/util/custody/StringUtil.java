package com.cxdai.console.util.custody;

/**
* @author YED
* 创建日期 2015-09-18
* Comment:
*/
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class StringUtil {

	public static String NoConvertCharSet = "UTF-8";

	public StringUtil() {
		super();
		// TODO 自动生成构�?函数存根
	}

	/**
	 * 左补字符串到指定长度
	 * 
	 * @param src
	 * @param pad
	 * @param len
	 * @return
	 */
	public static String lpad(String src, char pad, int len) {
		String targ = src;
		while (targ.length() < len)
			targ = pad + targ;
		return targ;
	}

	/**
	 * 右补字符串到指定长度
	 * 
	 * @param src
	 * @param pad
	 * @param len
	 * @return
	 */
	public static String rpad(String src, char pad, int len) {
		String targ = src;
		while (targ.length() < len)
			targ = targ + pad;
		return targ;
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-9-21
	 * 
	 * comment:剪除左侧字符
	 * @param src
	 * @param ch
	 * @return
	 */
	public static String trimLeft(String src, char ch) {
		int n = 0;
		for (n = 0; n < src.length(); n++) {
			if (src.charAt(n) != ch)
				break;
		}
		if (n == src.length())
			return (ch + "");
		return src.substring(n);
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-9-21
	 * 
	 * comment:剪除右侧字符
	 * @param src
	 * @param ch
	 * @return
	 */
	public static String trimRight(String src, char ch) {
		int n = 0;
		for (n = src.length(); n > 0; n--) {
			if (src.charAt(n - 1) != ch)
				break;
		}
		if (n == 0)
			return (ch + "");
		return src.substring(0, n);
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-9-21
	 * 
	 * comment:剪除右侧字符
	 * @param src
	 * @param ch
	 * @return
	 */
	public static String trimRight(byte[] src, char ch) {
		int n = 0;
		for (n = src.length; n > 0; n--) {
			if (src[n - 1] != ch)
				break;
		}
		if (n == 0)
			return (ch + "");
		return new String(src, 0, n);

	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-9-21
	 * 
	 * comment:剪除左侧字符
	 * @param src
	 * @param ch
	 * @return
	 */
	public static String trimLeft(byte[] src, char ch) {
		int n = 0;
		for (n = 0; n < src.length; n++) {
			if (src[n] != ch)
				break;
		}
		if (n == src.length)
			return (ch + "");
		return new String(src, n, src.length - n);
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-9-21
	 * 
	 * comment:去除字符串中指定的字�?
	 * @param src
	 *            ，要处理的字符串
	 * @param ex
	 *            要删除的字符集合
	 * @return 处理后的字符�?
	 */
	public static String exclude(String src, String ex) {
		StringBuffer sNew = new StringBuffer();
		for (int n = 0; n < src.length(); n++) {
			if (ex.indexOf(src.charAt(n)) == -1)
				sNew.append(src.charAt(n));
		}
		return sNew.toString();
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-12-16
	 * 
	 * comment:获取字符串对应的数组
	 * @param msg
	 * @param charSet
	 * @return
	 */
	public static byte[] string2Bytes(String msg, String charSet) {
		try {
			return msg.getBytes(charSet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @author Bryan 创建日期 2005-12-16
	 * 
	 * comment:使用缺省的字符集合获取字符串的存放数�?
	 * @param msg
	 * @return
	 */
	public static byte[] string2Bytes(String msg) {
		try {
			return msg.getBytes(NoConvertCharSet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String bytes2String(byte[] msg, String charSet) {
		try {
			return new String(msg, charSet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String bytes2String(byte[] msg) {
		return bytes2String(msg, NoConvertCharSet);
	}

	/**
	 * 格式化今天，yyyyMMdd
	 * 
	 * @return
	 */
	public static String formatToday() {
		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(now);
	}

	/**
	 * 格式化现在，HHmmss
	 * 
	 * @return
	 */
	public static String formatNow() {
		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("HHmmss");
		return fmt.format(now);
	}

	public static String base64Encode(byte[] bytes) {
		return bytes2String(Base64.encode(bytes));
	}
	
	public static String base64EncodeUTF(byte[] bytes){
		return new String(Base64.encode(bytes));
	}

/*	public static byte[] base64Decode(String string) {
		return Base64.decode(string2Bytes(string));
	}*/
	
	public static byte[] base64DecodeUTF(String string){
		try{
			return Base64.decode(string.getBytes("UTF-8"));
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
    public static byte[] addByteSuffix(byte abyte0[], int i)
    {
        byte abyte1[] = new byte[i];
        int j = abyte0.length < i ? abyte0.length : i;
        for(int k = 0; k < j; k++)
            abyte1[k] = abyte0[k];

        if(j < i)
        {
            for(int l = j; l < i; l++)
                abyte1[l] = 32;

        }
        return abyte1;
    }
    
    public static char[] addCharSuffix(char ac[], int i)
    {
        char ac1[] = new char[i];
        int j = ac.length < i ? ac.length : i;
        for(int k = 0; k < j; k++)
            ac1[k] = ac[k];

        if(j < i)
        {
            for(int l = j; l < i; l++)
                ac1[l] = ' ';

        }
        return ac1;
    }
    
    public static char[] nvlArray(int i)
    {
        char ac1[] = new char[i];
        for(int k = 0; k < i; k++)
            ac1[k] = ' ';
        
        return ac1;
    }
    
    public static String trimNvl(String str){
    	if(str==null){
    		return "";
    	}
    	return str.trim();
    }
    
	/**
	 * 从XML报文中找到tagName的�?返回
	 * @param content
	 * @param tagName
	 * @return
	 */
	public static String getTagValue(String content, String tagName) {
		String value = "";
		String startTag = "<" + tagName + ">";
		String endTag = "</" + tagName + ">";
		int length = startTag.length();

		String xmlContent = content;

		int indexStart = xmlContent.indexOf(startTag);
		int indexEnd = xmlContent.indexOf(endTag);

		if (indexEnd <= indexStart)
			return value;

		value = xmlContent.substring(indexStart + length, indexEnd);

		return value;
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    String str="wangjianming";
	    System.out.println("======"+base64EncodeUTF(str.getBytes()));
	    
		System.out.println(new String(StringUtil.nvlArray(12)));

	}
}

