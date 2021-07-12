package com.ezen.springmyworkspace.opendata.covid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidDailyController {

	private CovidDailyRepository repo;

	@Autowired
	public CovidDailyController(CovidDailyRepository repo) {
		this.repo = repo;
	}

	// @Cacheable ���� ��ü�� ĳ����
	// cacheNames: ĳ���� ��ü�� ��Ī(���Ƿ� ����)
	// key: ĳ���� ��ü�� key

	@Cacheable(cacheNames = "covid-daily", key = "0")
	@GetMapping(value = "/opendata/covid/daily")
	public List<CovidDaily> getListOrderByDataTime() {
		Order[] orders = { new Order(Sort.Direction.DESC, "stdDay"), new Order(Sort.Direction.ASC, "gubun") };

		return repo.findAll(PageRequest.of(0, 133, Sort.by(orders))).toList();
	}
}
