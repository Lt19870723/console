package com.cxdai.console.util.custody;

/**
 * 数据加密Strategy
 * @author YED
 * @version 2015-09-18
 */
public abstract class DataProcessRule {
	
	/**
	 * 处理前的数据
	 */
	protected String dataBP ;
	
	/**
	 * 处理后的数据
	 */
	protected String dataAP;
	
	/**
	 * 是否通过处理
	 */
	protected boolean passed;
	
	/**
	 * 数据操作
	 */
	public abstract void dataProcess();
	
	/**
	 * 数据逆操�?
	 */
	public abstract void dataProcessDE();
	/**
	 * @return Returns the dataAP.
	 */
	public String getDataAP() {
		return dataAP;
	}
	/**
	 * @param dataAP The dataAP to set.
	 */
	public void setDataAP(String dataAP) {
		this.dataAP = dataAP;
	}
	/**
	 * @return Returns the dataBP.
	 */
	public String getDataBP() {
		return dataBP;
	}
	/**
	 * @param dataBP The dataBP to set.
	 */
	public void setDataBP(String dataBP) {
		this.dataBP = dataBP;
	}

	/**
	 * @return Returns the passed.
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * @param passed The passed to set.
	 */
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	
	

}
