package com.leanlee.memory.back.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 饭店信息
 *
 * @author Lean
 * @since 2021-10-14 16:58
 **/
@Data
@Builder
public class Restaurant {

	private String id;

	/**
	 * 饭店名称
	 */
	private String name;

	/**
	 * 花费
	 */
	private String total;

	/**
	 * 就餐人数
	 */
	private int guests;

	/**
	 * 时间
	 */
	private String timestamp;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 维度
	 */
	private String latitude;

	/**
	 * 总结
	 */
	private String summary;

	/**
	 * 是否有分店
	 */
	private boolean branch;

	/**
	 * 数据来源
	 */
	private String url;

}
