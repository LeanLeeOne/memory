package com.leanlee.memory.back;

import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;
import com.leanlee.memory.back.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 点位搜索
 * @author: Lean
 * @create: 2021-10-14 16:58
 **/
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantMapper service;

	@RequestMapping("/query/")
	public List<Restaurant> query(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "false", required = false) String rows, @RequestParam(name = "offset", required = false) String offset){
		return service.query(id, rows, offset);
	}

	@RequestMapping("/sites/")
	public List<Site> sites(){
		return service.sites();
	}
}
