package com.leanlee.memory.back.entity;

/**
 * @author Lean
 */
public enum Operation {

	/**
	 * 增，删，改，查
	 */
	CREATE("create"),
	UPDATE("update"),
	READ("read"),
	DELETE("delete"),
	;

	/**
	 * 对应的值
	 */
	private final String value;

	Operation(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}