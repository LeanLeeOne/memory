package com.leanlee.memory.back;

import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;
import com.leanlee.memory.back.mapper.RestaurantMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

	private static final String URL_SEARCH_SITE = "https://api.map.baidu.com/place/v2/search?query={name}&region={region}&output=json&ak=M076MNMLZmNDOlsP1vsDkKTNSjn3qBgt";

	@Resource
	private RestaurantMapper service;
	@Resource
	private RestTemplate restTemplate;

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

	/**
	 * 根据名称查询地点
	 * 行政区划默认为北京
	 *
	 * @param name   地点名称
	 * @param region 行政区划
	 * @return 地点列表
	 */
	@RequestMapping("/search/")
	public String search(@RequestParam("name") String name, @RequestParam(name = "region", defaultValue = "北京") String region) {
		String response = restTemplate.getForObject(URL_SEARCH_SITE, String.class, name, region);
		// 将Unicode转为正常的字符
		response = StringEscapeUtils.unescapeJava(response);
		return response;
	}

}
