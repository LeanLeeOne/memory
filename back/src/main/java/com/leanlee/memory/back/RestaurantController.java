package com.leanlee.memory.back;

import com.leanlee.memory.back.entity.Operation;
import com.leanlee.memory.back.entity.OperationLog;
import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;
import com.leanlee.memory.back.mapper.OperationLogMapper;
import com.leanlee.memory.back.mapper.RestaurantMapper;
import com.leanlee.memory.back.util.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 饭店
 *
 * @author Lean
 * @date 2021-10-14 16:58
 **/
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

	private static final String URL_SEARCH_SITE = "https://api.map.baidu.com/place/v2/search?query={name}&region={region}&output=json&ak=M076MNMLZmNDOlsP1vsDkKTNSjn3qBgt";

	@Resource
	private RestaurantMapper restaurantMapper;
	@Resource
	private OperationLogMapper operationLogMapper;
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("/count/")
	public int count() {
		return restaurantMapper.count();
	}

	@RequestMapping("/query/")
	public List<Restaurant> query(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "offset", defaultValue = "0") int offset, @RequestParam(name = "limit", defaultValue = "10") int limit) {
		return restaurantMapper.query(id, offset, limit);
	}

	@RequestMapping("/add/")
	public int add(HttpServletRequest request, @RequestBody Restaurant restaurant) throws UnsupportedEncodingException {
		String operator = RequestUtils.getCookie(request, "operator");
		if (StringUtils.isBlank(operator)) {
			return -1;
		}

		// 插入饭店
		int result = restaurantMapper.add(restaurant);
		if (result <= 0) {
			return result;
		}

		// 插入操作记录
		String id = restaurant.getId();
		String ip = RequestUtils.getIpAddress(request);
		String timestamp = RequestUtils.getTimestamp();
		OperationLog operationLog = OperationLog.builder()
				.relationId(id)
				.operator(operator)
				.operation(Operation.CREATE)
				.ip(ip)
				.module(PortalApplication.MODULE_NAME)
				.timestamp(timestamp)
				.build();

		return operationLogMapper.add(operationLog);
	}

	@RequestMapping("/sites/")
	public List<Site> sites() {
		return restaurantMapper.sites();
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
