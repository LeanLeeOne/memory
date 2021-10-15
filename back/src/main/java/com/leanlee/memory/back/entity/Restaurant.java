package com.leanlee.memory.back.entity;

/**
 * @description: 饭店信息
 * @author: Lean
 * @create: 2021-10-14 16:58
 **/
public class Restaurant {

	private String id;
	// 饭店名称
	private String name;
	// 花费
	private String total;
	// 就餐人数
	private int guests;
	// 时间
	private String timestamp;
	// 地址
	private String address;
	// 经度
	private String longitude;
	// 维度
	private String latitude;
	// 总结
	private String summary;
	// 是否有分店
	private boolean branch;
	// 数据来源
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getGuests() {
		return guests;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isBranch() {
		return branch;
	}

	public void setBranch(boolean branch) {
		this.branch = branch;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
