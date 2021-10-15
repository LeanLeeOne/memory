package com.leanlee.memory.back.mapper;

import com.leanlee.memory.back.entity.Restaurant;
import com.leanlee.memory.back.entity.Site;

import java.util.List;

public interface RestaurantMapper {

	List<Restaurant> query(String id, int offset, int limit);

	List<Site> sites();
}
