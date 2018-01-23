package com.linkGap.projectManage.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

public class ReturnResultUtil implements Serializable {
	/**
	 * @author renhengli
	 * @date 2016年9月18日
	 * @version 1.0
	 */
	private static final long serialVersionUID = -2157418101985044608L;
	private String resultCode;
	private String resultMsg;
	private Object resultValue;
	private Map<?, ?> extras = new HashMap<T, T>();

	public Map<?, ?> getExtras() {
		return this.extras;
	}

	public void setExtras(Map<?, ?> extras) {
		this.extras = extras;
	}

	public ReturnResultUtil() {
		this.resultCode = "00";
		this.resultMsg = "成功";
	}

	public ReturnResultUtil(Object resultValue) {
		this.resultCode = "00";
		this.resultMsg = "成功";
		this.resultValue = resultValue;
	}

	public ReturnResultUtil(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public ReturnResultUtil(String resultCode, String resultMsg, Object resultValue) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultValue = resultValue;
	}

	public ReturnResultUtil(String resultCode, String resultMsg, Object resultValue, Map<?, ?> extras) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultValue = resultValue;
		this.extras = extras;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(Object resultValue) {
		this.resultValue = resultValue;
	}

	public String toString() {
		return "ReturnResultUtil [resultCode=" + this.resultCode + ", resultMsg=" + this.resultMsg + ", resultValue=" + this.resultValue + "]";
	}
}