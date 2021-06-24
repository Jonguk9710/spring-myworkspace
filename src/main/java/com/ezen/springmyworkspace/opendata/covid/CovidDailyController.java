package com.ezen.springmyworkspace.opendata.covid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value = "/opendata/covid/daily")
	public List<CovidDaily> getListByDataType() {
		Order[] orders = { new Order(Sort.Direction.DESC, "stdDay") };

		return repo.findAll(PageRequest.of(0, 133, Sort.by(orders))).toList();
	}
}
