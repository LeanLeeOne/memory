package com.leanlee.memory.back.mapper;

import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;

import java.util.List;

/**
 * @author 李晓辉
 */
public interface RestaurantMapper {

	/**
	 * 查询饭店
	 *
	 * @param id     id
	 * @param offset offset
	 * @param limit  list
	 * @return 列表
	 */
	List<Restaurant> query(String id, int offset, int limit);

	/**
	 * 新增饭店
	 *
	 * @param restaurant 饭店
	 * @return 结果
	 */
	int add(Restaurant restaurant);

	/**
	 * 查询点位
	 *
	 * @return 列表
	 */
	List<Site> sites();
}
