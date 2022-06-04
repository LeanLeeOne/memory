package com.leanlee.memory.back.controller;

import com.leanlee.memory.back.entity.OperationLog;
import com.leanlee.memory.back.mapper.OperationLogMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志
 *
 * @author Lean
 * @since 2022-02-03 13:04
 **/
@RequestMapping("/operation/log")
@RestController
public class OperationLogController {

	@Resource
	private OperationLogMapper operationLogMapper;


	@RequestMapping("/query/")
	public List<OperationLog> query(@RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "limit", defaultValue = "10") int limit) {
		return operationLogMapper.query(offset, limit);
	}

}
