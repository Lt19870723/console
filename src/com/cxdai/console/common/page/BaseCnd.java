package com.cxdai.console.common.page;

import java.io.Serializable;

public class BaseCnd implements PageCnd, Serializable {
	private static final long serialVersionUID = 2902608270942175291L;
	
	private int _offset;
	private int _limit;

	public int get_offset() {
		return _offset;
	}

	public void set_offset(int _offset) {
		this._offset = _offset;
	}

	public int get_limit() {
		return _limit;
	}

	public void set_limit(int _limit) {
		this._limit = _limit;
	}
}
