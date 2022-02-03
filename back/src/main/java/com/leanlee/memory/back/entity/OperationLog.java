package com.leanlee.memory.back.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 操作日志
 *
 * @author Lean
 * @since 2022-02-03 11:26
 **/
@Data
@Builder
public class OperationLog {

	private String id;

	/**
	 * 关联ID
	 */
	private String relationId;

	/**
	 * 操作人
	 * 用姓名就可以
	 */
	private String operator;

	/**
	 * 操作
	 */
	private Operation operation;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 模块名
	 */
	private String module;

	/**
	 * 时间
	 */
	private String timestamp;

}
