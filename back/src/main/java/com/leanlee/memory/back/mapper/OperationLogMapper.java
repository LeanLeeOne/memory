package com.leanlee.memory.back.mapper;

import com.leanlee.memory.back.entity.OperationLog;

import java.util.List;

/**
 * 操作日志
 *
 * @author Lean
 * @since 2022-02-03 11:41
 **/
public interface OperationLogMapper {

	/**
	 * 查询操作记录
	 *
	 * @param offset offset
	 * @param limit  list
	 * @return 列表
	 */
	List<OperationLog> query(int offset, int limit);

	/**
	 * 新增操作日志
	 *
	 * @param operationLog 操作日志
	 * @return 结果
	 */
	int add(OperationLog operationLog);
}
