package com.leanlee.memory.back;

import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;
import com.leanlee.memory.back.mapper.RestaurantMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 点位搜索
 *
 * @author Lean
 * @date 2021-10-14 16:58
 **/
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

	@Resource
	private RestaurantMapper service;

	@RequestMapping("/count/")
	public int count() {
		return service.count();
	}

	@RequestMapping("/query/")
	public List<Restaurant> query(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "limit", defaultValue = "10") int limit) {
		return service.query(id, offset, limit);
	}

	@RequestMapping("/add/")
	public int add(@RequestBody Restaurant restaurant) {
		return service.add(restaurant);
	}

	@RequestMapping("/sites/")
	public List<Site> sites() {
		return service.sites();
	}
}
