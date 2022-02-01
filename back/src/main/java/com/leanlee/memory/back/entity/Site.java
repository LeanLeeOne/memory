package com.leanlee.memory.back.entity;

import lombok.Data;

/**
 * 精简的点位
 *
 * @author Lean
 * @date 2021-10-14 17:32
 **/
@Data
public class Site {

	private String id;

	/**
	 * 经度
	 */
	private String lng;

	/**
	 * 纬度
	 */
	private String lat;

	/**
	 * 是否有分店
	 * 无分店用红色图表，有分店用蓝图标
	 */
	private Boolean branch;
}
