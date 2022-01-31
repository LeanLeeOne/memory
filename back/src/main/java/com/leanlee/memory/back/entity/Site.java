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
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

}
